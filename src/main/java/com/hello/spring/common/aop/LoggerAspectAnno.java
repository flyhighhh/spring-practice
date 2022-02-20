package com.hello.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

//Aspect클래스로 등록하기
@Component
@Aspect
@Slf4j
public class LoggerAspectAnno {
	
	//@Pointcut("execution(* com.hello.spring.member..*(..))")
	//(* com.hello.spring.memo.model.service..insert*(..)) 서비스에 있는 insert에 있는 거만
	//public void memberCheck() {}
	
//	@Before("memberCheck()")
//	public void loggerPrint(JoinPoint jp) {
//		Signature sig=jp.getSignature();
//		
//		log.debug("========= 어노테이션 aop ========");
//		log.debug(sig.getName()+":"+sig.getDeclaringTypeName());
//		log.debug("======== 어노테이션 aop 끝 =======");
//		
//	}
//	
//	@After("memberCheck()")
//	public void loggerAfter(JoinPoint jp) {
//		Signature sig=jp.getSignature();
//		
//		log.debug("========= 후 처리 어노테이션 aop ========");
//		log.debug(sig.getName()+":"+sig.getDeclaringTypeName());
//		log.debug("======== 후 처리 어노테이션 aop 끝 =======");
//	}
	
	//전후처리를 동시에 ~ Around
	@Pointcut("execution(* com.hello.spring.memo.controller..*(..))")
	public void aroundTest() {}
	//@Around("aroundTest()")
	@Around("execution(* com.hello.spring.memo.controller..*(..))")
	public Object checkTest(ProceedingJoinPoint join) throws Throwable{
		//Around의 ProceedingJoinPoint객체를 이용해서
		//전, 후를 나눠서 처리함 -> ProceedingJoinPoint.proceed()메소드를 이용
		log.debug("==== around 전처리 ====");
		StopWatch stop=new StopWatch();
		stop.start();
//		Object obj=join.proceed();
		return join.proceed();
//		log.debug("==== around 후처리 ====");
//		stop.stop();
//		Signature sig=join.getSignature();
//		log.debug(sig.getName()+"메소드 소요시간 : "+stop.getTotalTimeMillis()+"ms");
//		return obj;
		
	}
	
	
}
