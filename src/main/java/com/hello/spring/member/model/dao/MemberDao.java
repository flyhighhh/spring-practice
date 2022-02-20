package com.hello.spring.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.hello.spring.member.model.vo.Member;

public interface MemberDao {
	
	Member login(SqlSessionTemplate session, Map param);
	
	int insertMember(SqlSessionTemplate session, Member m);
}
