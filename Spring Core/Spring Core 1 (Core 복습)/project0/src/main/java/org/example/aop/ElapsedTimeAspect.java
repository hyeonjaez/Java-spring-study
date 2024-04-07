package org.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.example.message.MessageSender;
import org.springframework.util.StopWatch;


public class ElapsedTimeAspect {

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
