package edu.whu.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryann
 * @create 2023/10/31 - 14:32
 */
@Aspect
@Component
public class MonitorAspect {
    /**
     * 时间统计结果表， key是方法的signature
     */
    final Map<String,Long> invokeNumMetric = Collections.synchronizedMap(new HashMap<>());  // 调用次数

    public Map<String, Long> getInvokeNumMetric() {
        return invokeNumMetric;
    }

    public Map<String, Long> getMaxTimeMetric() {
        return maxTimeMetric;
    }

    public Map<String, Long> getMinTimeMetric() {
        return minTimeMetric;
    }

    public Map<String, Long> getAvgTimeMetric() {
        return avgTimeMetric;
    }

    public Map<String, Long> getExceptionNumMetric() {
        return exceptionNumMetric;
    }

    final Map<String,Long> maxTimeMetric = Collections.synchronizedMap(new HashMap<>());  // 最长响应时间
    final Map<String,Long> minTimeMetric = Collections.synchronizedMap(new HashMap<>());  // 最短响应时间
    final Map<String,Long> avgTimeMetric = Collections.synchronizedMap(new HashMap<>());  // 平均响应时间
    final Map<String,Long> exceptionNumMetric = Collections.synchronizedMap(new HashMap<>());  // 发生异常次数

    /**
     * controller包下的类的方法都会被统计，统计结果写入结果表
     */
    @Around("execution(* edu.whu.demo.controller.*.*(..))")
    public Object calculateTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodSig = joinPoint.getSignature().toString();
        Object retValue = null;

        long t1 = Calendar.getInstance().getTimeInMillis();
        try {
            retValue = joinPoint.proceed();  // 调用被拦截的方法，将返回值存储在retValue中
        } catch (Exception e) {  // 发生异常，更新异常计数器
            long exceptionNum = exceptionNumMetric.getOrDefault(methodSig, 0L);
            exceptionNumMetric.put(methodSig, exceptionNum + 1);
        } finally {
            long t2 = Calendar.getInstance().getTimeInMillis();
            long responseTime = t2 - t1;

            long invokeNum = invokeNumMetric.getOrDefault(methodSig, 0L);
            invokeNumMetric.put(methodSig, invokeNum + 1);

            long base = maxTimeMetric.getOrDefault(methodSig, 0L);
            long maxTime = Math.max(base, responseTime);
            maxTimeMetric.put(methodSig, maxTime);

            base = minTimeMetric.getOrDefault(methodSig, Long.MAX_VALUE);
            long minTime = Math.min(base, responseTime);
            minTimeMetric.put(methodSig, minTime);

            long avgTime = avgTimeMetric.getOrDefault(methodSig, 0L);
            avgTimeMetric.put(methodSig, (avgTime * invokeNum + responseTime) / (invokeNum + 1));
        }
        return retValue;
    }
}
