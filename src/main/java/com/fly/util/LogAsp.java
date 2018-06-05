package com.fly.util;

import com.fly.bean.business.SystemLogMessage;
import com.fly.service.SystemLogMessageService;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect                //切面
@Component             //Bean
public class LogAsp implements Ordered {

    @Autowired
    SystemLogMessageService logMessageService;

    private String getServiceMethodDescription(ProceedingJoinPoint j){
        String str="";
        Class clazz = j.getTarget().getClass();
        Object[] objects = j.getArgs();
        String methodName = j.getSignature().getName();
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            if (m.getName().equals(methodName)){
                if(m.getParameterTypes().length==objects.length){
                   str =m.getAnnotation(SystemMethodLog.class).describe();
                }
            }
        }
        return str;
    }


    @Around("@annotation(com.fly.util.SystemMethodLog)")
    public Object logBefore(ProceedingJoinPoint j){

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        SystemLogMessage systemLogMessage =new SystemLogMessage();
        systemLogMessage.setName("小明");
        systemLogMessage.setRoles("admin");

        Object object = null;
        String description=getServiceMethodDescription(j);
        systemLogMessage.setDescription(description);
        Object[] objects = j.getArgs();
        String methodName = j.getSignature().getName();
        systemLogMessage.setMethod(methodName);
        systemLogMessage.setStartTime(new Date());
        Long startTime = System.currentTimeMillis();
        try {
            object=j.proceed(objects);
            systemLogMessage.setSuccessful("成功");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            systemLogMessage.setSuccessful("失败");
            systemLogMessage.setException(throwable.toString());
        }
        Gson gson = new Gson();
        Long endTime = System.currentTimeMillis();
        systemLogMessage.setSumTime(endTime-startTime+"ms");
        systemLogMessage.setParams(objects.length==0?"":gson.toJson(objects));
        System.out.println(methodName+"-"+description+"耗时"+(endTime-startTime));
        logMessageService.insert(systemLogMessage);
        return object;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}

