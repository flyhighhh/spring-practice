package com.hello.spring.demo.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hello.spring.demo.model.dao.DemoDao;
import com.hello.spring.demo.model.vo.Demo;

@Service
//component등록
public class DemoServiceImpl implements DemoService{
	
	@Autowired
	private DemoDao dao;
	
	@Autowired
	private SqlSessionTemplate session;
	

	@Override
	public int insertDemo(Demo demo) {
		int result=dao.insertDemo(session, demo);
		return result;
	}
	
	@Override
	public List<Demo> selectDemoList(){
		return dao.selectDemoList(session);
	}
	
	@Override
	public List<Map> selectDemoListMap(){
		return dao.selectDemoListMap(session);
	}

	
	
}
