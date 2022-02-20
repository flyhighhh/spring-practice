package com.hello.spring.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.hello.spring.demo.model.service.DemoService;
import com.hello.spring.demo.model.vo.Demo;

@Controller //주소를 받았을때 기능을 수행하는 컨트롤러
@RequestMapping("/demo")
//이렇게하면 기본으로 앞에 붙게돼서 아래에 클래스 매핑할 때 안써도 됨 
public class DemoController {
	
	//의존성등록
	@Autowired
	private DemoService service;
	//log4j를 이용해서 로그를 출력하려면 Logger클래스를 생성해서 이용
	private Logger logger=LoggerFactory.getLogger(DemoController.class);

	//요청을 처리하는 메소드를 등록 함
	//기본적으로 String을 반환하는 메소드 
	// -> springBean으로 등록된 InternalResourceViewResolver에 전달되어 view 파일을 찾아서 응답함
	//controller에 등록된 메소드는 요청에 대한 응답을 처리하는 것으로
	//요청(주소)을 받을 수 있는 설정 -> 주소매핑을 해주는 설정을 해야 함
	//@RequestMapping("주소값")||@RequestMapping(name="",)

	@RequestMapping("/demo.do")
	public String demoView() {
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
		// 화면을 출력해주는 응답을 하자
		// return "view이름" -> /WEB-INF/views/demo.jsp; 이렇게하면 못찾음
		return "demo/demo";	//prefix: WEB-INF/views    suffix=.jsp
	}

	/*
	 * controller에 선언한 메소드는 서블릿에서 doGet,doPost()메소드와 동일한 기능을 함
	 * controller에 선언한 메소드도! 매개변수로 프론트(화면)에서 보낸 데이터를 가져올 수 있다.
	 * 선언이 가능한 매개변수가 정해져있다.
	 * 1. HttpServletRequest
	 * 2. HttpServletResponse
	 * 3. HttpSession
	 * 4. java.util.Locale(지역정보)
	 * 5. InputStream/Reader
	 * 6. OutputStream/Writer
	 * 
	 * -------- 데이터를 저장하는 매개변수 선언 --------
	 * 7. vo객체 선언 -> 파라미터의 name값과 일치하는 멤버변수에 값을 대입해서 생성
	 * 8. Map 선언 -> key:value형식으로 파라미터의 name:value값을 그대로 받아옴
	 * 
	 * 9. Model -> jsp에 전달한 데이터를 보관하는 객체
	 * 10. ModelAndView -> jsp에 전달할 데이터와 연결할 view이름을 저장하는 객체
	 * 
	 * -------- 어노테이션을 선언하여 값을 받는 매개변수 ------
	 * @RequestParam(value="parmeter name값") name값을 저장할 자료형 변수명
	 * @RequestHeader(value="header key값") header데이터 저장할 자료형 변수명
	 * @CookieValue(value="cookie key값")
	 * 
	 * -------- 추가어노테이션 --------
	 * @PathVariable("값") 자료형 변수명 : 요청주소에 있는 값을 불러올 때 사용
	 *  -> REST방식으로 주소가 설계되어있을 때 주소의 데이터를 가져올 때 사용
	 *  -> boardView.do?no=1 / board/1
	 * 
	 * @ResponseBody : 반환되는 데이터를 JSON으로 반환해줌. views리졸버를 통과하지 않고 그냥 json데이터로 반환하는 메소드
	 * 
	 */

	//서블릿방식으로 데이터를 받아보자
	@RequestMapping("/demo1.do")
	public String demo1(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException{
			System.out.println(req);
			System.out.println(res);
			System.out.println(req.getParameter("devName"));
			System.out.println(req.getParameter("devAge"));
			System.out.println(req.getParameter("devEmail"));
			System.out.println(req.getParameter("devGender"));
			for(String l : req.getParameterValues("devLang")) {
				System.out.println(l);
			}
			Demo d=Demo.builder()
					.devName(req.getParameter("devName"))
					.devAge(Integer.parseInt(req.getParameter("devAge")))
					.devGender(req.getParameter("devGender"))
					.devEmail(req.getParameter("devEmail"))
					//.devLang(req.getParameterValues("devLang"))
					.build();
			req.setAttribute("demo", d);
			
			System.out.println("session출력하기");
			System.out.println(session.getAttribute("userId"));
			System.out.println(session.getAttribute("password"));
			
			
//			res.setContentType("text/html;charset=utf-8");
//			res.getWriter().write("전송완료");

			return "demo/demoResult";	//화면전환 ->RequestDispatcher.forward();
	}
		
	//프론트에서 보낸 데이터를 1:1로 매칭해서 데이터 받기
	//@requestParam어노테이션을 이용한다.
	@RequestMapping("/demo2.do")
	public String demo2(
			@RequestParam(value="devName") String name,
			@RequestParam(value="devAge", defaultValue="10") int age,
			@RequestParam(value="devGender") String gender,
			@RequestParam(value="devEmail") String email,
			@RequestParam(value="devLang",required=false) String[] dlang,
			Model m){
			
		System.out.println(name+age+gender+email);
//		for(String l : dlang) {
//			System.out.println(l);
//		}
		Demo d=Demo.builder()
				.devName(name).devAge(age)
				.devGender(gender).devEmail(email).build();
				//.devLang(dlang)
		//request클래스를 이용하지 않고 Model을 이용해서
		//필요한 데이터를 view에 전달할 수 있음 == request와 같은 scope범위를 가짐
		m.addAttribute("demo",d);
		return "demo/demoResult";
	}
	
	
	//1:1로 전송된 데이터를 받을 때
	//매개변수에 전송데이터의 key값과일치하게 변수명을 설정하면
	//@RequestParam어노테이션 없이 자동으로 대입 됨
	@RequestMapping(value="/autoParam.do")
	public String autoParam(String devName, String devEmail, 
					int devAge, String devGender, String[] devLang, Model model) {
		System.out.println(devName+devAge+devEmail+devGender);
		for(String l:devLang) {
			System.out.println(l);
		}
		
		Demo d=Demo.builder().devName(devName).devAge(devAge)
				.devEmail(devEmail).devGender(devGender).build();
				//.devLang(devLang)
		
		model.addAttribute("demo",d);
		
		return "demo/demoResult";
	}
	
	
	//Command로 전송된 데이터 받기
	//vo객체로 데이터 직접받기!
	//멤버변수명과 전달되는 데이터의 key명칭이 동일해야함.
	@RequestMapping(value="/demo3.do")
	public String demo3(Demo m, Model model) {
		model.addAttribute("demo",m);
		return "demo/demoResult";
		
	}

	//vo도 만들기 귀찮아, 그냥 있는 그대로 받자 Map으로 받아서, Map으로 넘기기
	//vo없이 모든 전달된 데이터 받아오기
	@RequestMapping(value="/demo4.do")
	public String demo4(@RequestParam Map param, String[] devLang, Model model) {
		//배열은 못받았음 String[] devLang 추가
		System.out.println(param);
		param.put("devLang", devLang);	//덮어쓰기 해줌
		model.addAttribute("demo",param);
		return "demo/demoResult";
	}
	
	//추가적인 정보를 가져오는 매개변수
	//HttpHeader의 정보, Cookie정보를 가져오기
	@RequestMapping(value="/demo5.do")
	public String demo5(@RequestHeader(value="User-agent") String userAgent,
			@RequestHeader(value="Referer") String prevPath,
			@CookieValue(value="test",required = false) String cookieVal,
			@SessionAttribute(value="uerId",required = false) String userId,
			Model m) {
			System.out.println(userAgent);
			System.out.println(prevPath);
			System.out.println(cookieVal);
			System.out.println(userId);
		
		return "demo/demo";
	}
	
	
//	@RequestMapping(value="/demo6.do",method=RequestMethod.GET)
//	public String getTest() {
//		//get으로만 요청해야함, post쓰면 post만 된다
//		return "test";
//	}
	
	
	@RequestMapping(value="/demo6.do")
	@ResponseBody	//viewResolver를 통과하지 않고 문자열(객체) JSON을 브라우저에 응답함
	public String getTest2() {
		return "testResponseBody";
	}
	
	
	@RequestMapping(value="/insertDemo.do", method=RequestMethod.POST)
	public String insertDemo(Demo dev) {
		int result=service.insertDemo(dev);
		System.out.println(result>0?"입력성공":"입력실패");
		return "demo/demo";
	}
	
	@RequestMapping("/demoList.do")
	public String selectDemoList(Model model){
		List<Demo> list=service.selectDemoList();
		model.addAttribute("list",list);
		return "demo/demoList";
	}
	
	@RequestMapping("/demoListMap.do")
	public String selectDemoListMap(Model model) {
		model.addAttribute("list",service.selectDemoListMap());
		return "demo/demoList";
	}
	
	

}
