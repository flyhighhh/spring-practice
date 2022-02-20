package com.hello.spring.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hello.spring.memo.model.service.MemoService;
import com.hello.spring.memo.model.vo.Memo;

@Controller
@RequestMapping("/memo")
public class MemoController {
	
	@Autowired
	private MemoService service;
	
	@RequestMapping("/memoList.do")
	public String selectMemoList(Model model) {
		List<Memo> list=service.selectMemoList();
		model.addAttribute("list",list);
		return "memo/memoList";
	}
	@RequestMapping("/insertMemo.do")
	public ModelAndView insertMemo(Memo m, ModelAndView mv) {
		//ModelAndView클래스를 이용하면 한번에 view, model데이터를 관리할 수 있다.
		//controller메소드의 반환형으로 사용할 수 있음.
		//return new ModelAndView("memo/memoList",model);
		int result=service.insertMemo(m);
						
		mv.setViewName("redirect:memoList.do");
		mv.addObject("msg","데이터");
		return mv;
	}
//	@RequestMapping("/deleteMemo.do")
//	public 
	
}
