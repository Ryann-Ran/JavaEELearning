package edu.whu.demo.aspect;

import lombok.Data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Data
public class ApiMetric {

    int minExecutionTime = 0;

    int maxExecutionTime = 0;

    int executionCount = 0;

    int totalExecutionTime = 0;

    Map<String, Integer> exceptionCountMap = Collections.synchronizedMap(new HashMap<>());

    public void addTime(int time){
        totalExecutionTime +=time;
        executionCount++;
        if(time>maxExecutionTime){
            maxExecutionTime=time;
        }else if(time<minExecutionTime){
            minExecutionTime=time;
        }
    }

    public void addException(Throwable e){
        String exceptionName = e.getClass().getName();
        Integer base = exceptionCountMap.get(exceptionName);
        exceptionCountMap.put(exceptionName,base==null?1:base+1);
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append("ExecutionCount:"+executionCount+"\n");
        builder.append("TotalExecutionTime:"+totalExecutionTime+"\n");
        builder.append("AverageExecutionTime:"+(float)totalExecutionTime/executionCount+"\n");
        builder.append("MinExecutionTime:"+minExecutionTime+"\n");
        builder.append("MaxExecutionTime:"+maxExecutionTime+"\n");
        builder.append("ExceptionCounts:\n");
        exceptionCountMap.forEach((e,count)->{
           builder.append(" "+e+":"+count+"\n");
        });
        return builder.toString();
    }
}
