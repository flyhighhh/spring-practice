package com.hello.spring.common.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.hello.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class AuthenticationCheck {

	@Before("execution(* com.hello.spring..insert*(..))")
	public void check(JoinPoint jp) {
		//로그인여부를 확인 후 insert 실행하기
		log.debug("==== 로그인체크하기 =====");
		HttpSession session=(HttpSession)RequestContextHolder.currentRequestAttributes()
			.resolveReference(RequestAttributes.REFERENCE_SESSION);
		Member loginMember=(Member)session.getAttribute("loginMember");
		
		//메소드 실행 시 전달되는 매개변수 확인하기
		Object[] args=jp.getArgs();
		for(Object p: args) {
			log.debug(p.toString());
		}
		
		
		if(loginMember==null) {
			//aop에서 로직을 중단하고싶을 때 Exception을 발생시킨다
			try {
				throw new Exception("사용자 이용권한이 없습니다!");
			}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		//spring이 제공하는 객체 리퀘스트에 대한 값들을 가져올 수 있음
	}
	
}
