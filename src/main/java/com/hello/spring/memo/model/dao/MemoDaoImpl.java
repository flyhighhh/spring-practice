package com.hello.spring.memo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.hello.spring.memo.model.vo.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {
	
	@Override
	public List<Memo> selectMemoList(SqlSessionTemplate session){
		return session.selectList("memo.selectMemoList");
	}
	@Override
	public int insertMemo(SqlSessionTemplate session, Memo m) {
		return session.insert("memo.insertMemo",m);
	}
}
