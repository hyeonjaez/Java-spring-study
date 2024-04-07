package com.nhnacademy.edu.springframework.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ElapsedTimeAspect {

    @Around("@annotation(com.nhnacademy.edu.springframework.project.annotation.TimeChecker)")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().getSimpleName();

        StopWatch stopWatch = new StopWatch(methodName);

        try {
            stopWatch.start();
            return pjp.proceed();
        } finally {
            stopWatch.stop();
            System.out.println("[" + className + "].[" + methodName + "] " + stopWatch.getTotalTimeMillis() + "ms");
        }
    }

}
