package com.lyun55.learn_spring_aop.aopexample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	// PointCut
	@Before("execution(* com.lyun55.learn_spring_aop.aopexample.*.*.*(..))")
	public void logMethodCallBeforeExecution(JoinPoint joinpoint) {
		// Logic
		logger.info("Before Aspect - {} is called - {} ",joinpoint, joinpoint.getArgs());
	}
	@After("execution(* com.lyun55.learn_spring_aop.aopexample.*.*.*(..))")
	public void logMethodCallAfterExecution(JoinPoint joinpoint) {
		logger.info("After Aspect - {} has executed",joinpoint);
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.lyun55.learn_spring_aop.aopexample.*.*.*(..))",
			throwing = "exception"
			)
	public void logMethodCallAfterException(JoinPoint joinpoint, Exception exception) {
		logger.info("AfterThrowing Aspect - {} has thrown exception {} ",joinpoint, exception);
	}
	
	@AfterReturning(
			pointcut = "execution(* com.lyun55.learn_spring_aop.aopexample.*.*.*(..))",
			returning = "resultValue"
			)
	public void logMethodCallAfterSuccessfulExecution(JoinPoint joinpoint, Object resultValue) {
		logger.info("AfterReturning Aspect - {} has returned {} ",joinpoint, resultValue);
	}
}
