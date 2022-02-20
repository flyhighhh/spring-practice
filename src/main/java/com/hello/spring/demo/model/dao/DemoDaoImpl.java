package com.hello.spring.demo.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.hello.spring.demo.model.vo.Demo;

@Repository
public class DemoDaoImpl implements DemoDao{

	@Override
	public int insertDemo(SqlSessionTemplate session, Demo demo) {
		return session.insert("dev.insertDemo",demo);
	}
	
	@Override
	public List<Demo> selectDemoList(SqlSessionTemplate session){
		return session.selectList("dev.selectDemoList");
	}
	
	@Override
	public List<Map> selectDemoListMap(SqlSessionTemplate session){
		return session.selectList("dev.selectDemoListMap");
	}
}

