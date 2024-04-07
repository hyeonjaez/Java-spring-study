package org.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ElapsedTimeAspect {

    @Around("@annotation(org.example.annotation.TimeChecker) && target(messageSender)")

    public Object logging(ProceedingJoinPoint pjp, Object messageSender) throws Throwable {
        String methodName = pjp.getSignature().getName();
        String className = messageSender.getClass().getSimpleName();

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
