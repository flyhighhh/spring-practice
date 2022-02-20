package com.hello.spring.ajax.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.spring.board.model.service.BoardService;
import com.hello.spring.board.model.vo.Board;
import com.hello.spring.member.model.service.MemberService;
import com.hello.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AjaxController {

	@Autowired
	private MemberService service;
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/ajax/checkId")
	public void checkIdBasic(String userId, HttpServletResponse response) throws IOException{
		log.debug(userId);	//enrollmember에서 data로 보낸거 받는거
		
		Member m=service.login(Map.of("userId", userId));
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(m!=null?false:true);
	}
	
	@RequestMapping("/ajax/jacksonCheckId")
	@ResponseBody
	public Map jacksonCheckId(String userId) {
		Member m=service.login(Map.of("userId",userId));
		
		List<Board> boards=boardService.selectBoardList(1, 10);
		
		//return m!=null?false:true;	//반환형 boolean
		//return m; //반환형 Member로 멤버전체 받아올 수 있음
		return Map.of("member",m,"list",boards);	//이때는 반환형 Map
	}
	
}
