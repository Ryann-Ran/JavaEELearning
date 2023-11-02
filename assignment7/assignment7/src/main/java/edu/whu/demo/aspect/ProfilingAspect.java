package edu.whu.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Aspect
@Component
public class ProfilingAspect {

    /**
     * 统计结果表
     */
    Map<String, ApiMetric> metrics = Collections.synchronizedMap(new HashMap<>());

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object doProfiling(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toString();
        ApiMetric apiMetric= metrics.get(method);
        if (apiMetric == null) {
            apiMetric = new ApiMetric();
            metrics.put(method,apiMetric);
        }
        try {
            long t1= Calendar.getInstance().getTimeInMillis();
            Object result = joinPoint.proceed();
            long t2= Calendar.getInstance().getTimeInMillis();
            apiMetric.addTime((int)(t2-t1));
            return result; // 注意要返回值，否则方法的返回值会变成null
        } catch (Throwable e) {
            apiMetric.addException(e);
            throw e;
        }
    }

    public Map<String, ApiMetric> getMetrics() {
        return metrics;
    }
}
