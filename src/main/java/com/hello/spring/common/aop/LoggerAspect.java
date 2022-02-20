package com.hello.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerAspect {
	
	public void loggerBefore(JoinPoint jp) {
		//JoinPoint : joinpoint에서 사용할 수 있는 각종 정보
		//호출된 메소드정보 : Signature클래스를 이용
		Signature sig=jp.getSignature();
		
		
		log.debug("======= aop before 적용 =======");
		log.debug(sig.getDeclaringType()+" : "+sig.getModifiers()
					+" : "+sig.getName()+":"+sig.getDeclaringTypeName());
		log.debug("======== aop before 끝 ========");
	}
}
