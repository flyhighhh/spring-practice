package com.hello.spring.demo.model.service;

import java.util.List;
import java.util.Map;

import com.hello.spring.demo.model.vo.Demo;

public interface DemoService {
	
	public int insertDemo(Demo demo);
	
	List<Demo> selectDemoList();
	
	List<Map> selectDemoListMap();
}
