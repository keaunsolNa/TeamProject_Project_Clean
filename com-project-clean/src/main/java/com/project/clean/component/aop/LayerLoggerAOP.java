package com.project.clean.component.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
 
@Aspect
@Component
public class LayerLoggerAOP {
 
	@Around("execution(* com.project.clean.controller..*(..))")
	public Object loggerTimerController(ProceedingJoinPoint joinPoint) throws Throwable {
		
		System.out.println("Controller Layer 시작.");
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object result = joinPoint.proceed();	
		
		stopWatch.stop();
		
		System.out.println(joinPoint.toString());
		System.out.println("총 소요 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)" + ", Controller Layer 종료");
		
		return result;
	}
	
    @Around("execution(* com.project.clean.model.service..*(..))")
    public Object loggerTimerService(ProceedingJoinPoint joinPoint) throws Throwable {
    	
    	System.out.println("Service Layer 시작.");
    	
    	StopWatch stopWatch = new StopWatch();
    	stopWatch.start();
    	
    	Object result = joinPoint.proceed();	
    	
    	stopWatch.stop();
    	
    	System.out.println(joinPoint.toString());
    	System.out.println("총 소요 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)" + ", Serivce Layer 종료.");
    	
    	return result;
    }
    
    
    @Around("execution(* com.project.clean.model.repository..*(..))")
    public Object loggerTimerRepository(ProceedingJoinPoint joinPoint) throws Throwable {
    	
    	System.out.println("repository Layer 시작.");
    	StopWatch stopWatch = new StopWatch();
    	stopWatch.start();
    	
    	Object result = joinPoint.proceed();	
    	
    	stopWatch.stop();
    	
    	System.out.println(joinPoint.toString());
    	System.out.println("총 소요 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)" + ", Repository Layer 종료");
    	
    	return result;
    }
    
 
}