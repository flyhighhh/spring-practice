package com.hello.spring.memo.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hello.spring.memo.model.dao.MemoDao;
import com.hello.spring.memo.model.vo.Memo;

@Service
public class MemoServiceImpl implements MemoService {
	@Autowired
	private MemoDao dao;
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<Memo> selectMemoList(){
		return dao.selectMemoList(session);
	}
	@Override
	public int insertMemo(Memo m) {
		return dao.insertMemo(session,m);
	}
}
