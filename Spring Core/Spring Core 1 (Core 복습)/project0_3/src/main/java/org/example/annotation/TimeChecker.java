package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@EnableAspectJAutoProxy
public @interface TimeChecker {
}
