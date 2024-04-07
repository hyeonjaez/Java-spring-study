package org.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class ElapsedTimeAspect {

    @Around("@annotation(org.example.annotation.TimeChecker)")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().getSimpleName();

        StopWatch stopWatch = new StopWatch(methodName);
        try {
            stopWatch.start();
            return pjp.proceed();
        } finally {
            stopWatch.stop();
            log.info("[" + className + "].[" + methodName + "] " + stopWatch.getTotalTimeMillis() + "ms");
        }
    }

}
