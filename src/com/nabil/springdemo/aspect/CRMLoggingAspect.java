package com.nabil.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
   private static Logger myLogger = Logger.getLogger(CRMLoggingAspect.class.getName());
   
   @Pointcut("execution( * com.nabil.springdemo.dao.*.*(..) )")
   private void forDaoPackage() {}
   
   @Pointcut("execution(* com.nabil.springdemo.controller.*.*(..))")
   private void forControllerPackage() {}
   
   @Pointcut("execution(* com.nabil.springdemo.service.*.*(..) )")
   private void forServicePackage() {}
   
   
   @Pointcut("forDaoPackage() || forControllerPackage() || forServicePackage()")
   private void forAppFlow() {}
   
   
   @Before("forAppFlow()")
   public void before(JoinPoint joinPoint) {
	   String method = joinPoint.getSignature().toShortString();
	   myLogger.info("===Before Calling method: " + method);
	   
	   Object[] args = joinPoint.getArgs();
	   
	   for(Object arg : args) {
		   
		   myLogger.info("Arguments of the method: " + arg);
	   }
	   
   }
   
   @AfterReturning(pointcut = "forAppFlow()", returning = "returnedValue")
   public void after(JoinPoint joinPoint ,Object returnedValue) {
	   String method = joinPoint.getSignature().toShortString();
	   myLogger.info("===After Calling method: " + method);
	   
	   myLogger.info("===The name of the class: " + returnedValue.getClass().getName());
	   
	   myLogger.info("The returned Value: " + returnedValue);
   }
   
   
}
