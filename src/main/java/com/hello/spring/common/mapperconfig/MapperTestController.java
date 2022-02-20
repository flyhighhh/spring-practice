package com.hello.spring.common.mapperconfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.spring.board.model.vo.Board;
import com.hello.spring.common.mapper.MemberMapper;
import com.hello.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MapperTestController {
	
	@Autowired
	private MemberMapper mapper;
	
	@RequestMapping("/mapperTest.do")
	@ResponseBody
	public Member selectMember(String userId) {
		Member m=mapper.selectMember(userId);
		List<Member> result=mapper.selectMemberList();
		Board b=mapper.selectBoard(61);
		log.debug("{}",m);
		log.debug("{}",result);
		log.debug("{}",b);
		
		return m;
	}
	

}
