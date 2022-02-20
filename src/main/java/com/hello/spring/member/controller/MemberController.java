package com.hello.spring.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.hello.spring.member.model.service.MemberService;
import com.hello.spring.member.model.vo.Member;

@Controller
@SessionAttributes({"loginMember"})
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	//암호화처리 클래스 불러오기
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	private Logger logger=LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping("/member/login.do")
	//1번방법: public String login(@RequestParam Map param, HttpSession session) {
	//HttpSession객체를 이용하지 않고 Session scope데이터 관리하기
	//@SessionAttributes({모델저장데이터})어노테이션을 이용함.
	public String login(@RequestParam Map param, Model model) {
		Member m=service.login(param);
		//같은 1234라도 암호화하면 달라짐 그래서 이렇게 해줘야해
		//matches(암호화되지 않은 비교 문자열, 암호화된 비교 문자열) --> true/false로 나옴
		
		logger.debug("{}",m);
		
		System.out.println(encoder.matches((String)param.get("password"),m.getPassword()));
		System.out.println(m);
		
		
		//1번방법: if(m!=null) session.setAttribute("loginMember", m);
		if(m!=null && encoder.matches((String)param.get("password"),m.getPassword())) {
			model.addAttribute("loginMember",m);
		}
		
		//RequestDispatcher.forward방식으로 전환되어 url이 전송되지 않음
		//sendRedirect방식으로 보내야함. (jsp직접접근X)
		//return "index";
		//반환하는 String값에 "redirect:주소" -> viewResolver에게 전달되지 않음!
		return "redirect:/";
		//home컨트롤러로 가서 index로 감
		
	}
	
	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session, SessionStatus status) {
		//model의 데이터를 @SessionAttributes어노테이션으로 sessionscope에 올렸으면
		//SessionStatus클래스를 이요해서 관리를 함.
		//session값을 제거할 때는 SessionStatus의 setComplete()메소드를 이용함.
		if(!status.isComplete()) {
			status.setComplete();
		}
		//session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/member/enrollMember.do")
	public String enrollMember() {
		return "/member/enrollMember";
	}
	
	@RequestMapping(value="/member/enrollMemberEnd.do", method=RequestMethod.POST)
	public String enrollMemberEnd(Member m, Model model) {
		//패스워드 암호화를 하려면 
		//encode(암호화할 문자열)메소드를 이용
//		System.out.println(m.getPassword());
//		System.out.println(encoder.encode(m.getPassword()));
		
		logger.debug("변경전 패스워드 : {}", m.getPassword());
		logger.debug("변경후 패스워드 : {}", encoder.encode(m.getPassword()));
		
		
		m.setPassword(encoder.encode(m.getPassword()));
		
		int result=service.insertMember(m);
		String msg="";
		String loc="";
		if(result>0) {
			msg="회원가입 성공";
			loc="/";
		}else {
			msg="회원가입 실패";
			loc="/member/enrollMember.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		return "common/msg";
	}
	
	
	
	
	
}
