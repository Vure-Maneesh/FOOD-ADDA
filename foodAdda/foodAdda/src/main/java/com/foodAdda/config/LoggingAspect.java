package com.foodAdda.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* com.foodAdda.service.*.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println(
                "Method will start now : Info is Coming from Aspect Class" + joinPoint.getSignature().getName());
    }

    @After("execution(* com.foodAdda.service.*.*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        System.out.println(
                "Method will end now : Info is Coming from Aspect Class" + joinPoint.getSignature().getName());
    }

}
