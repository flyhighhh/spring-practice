package com.hello.spring.member.model.service;

import java.util.Map;

import com.hello.spring.member.model.vo.Member;

public interface MemberService {
	
	Member login(Map param);
	int insertMember(Member m);
}
