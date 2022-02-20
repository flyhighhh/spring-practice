package com.hello.spring.demo.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.hello.spring.demo.model.vo.Demo;

public interface DemoDao {
	
	int insertDemo(SqlSessionTemplate session, Demo demo);
	List<Demo> selectDemoList(SqlSessionTemplate session);
	List<Map> selectDemoListMap(SqlSessionTemplate session);
}

