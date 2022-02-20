package com.hello.spring.memo.model.service;

import java.util.List;

import com.hello.spring.memo.model.vo.Memo;

public interface MemoService {
	
	List<Memo> selectMemoList();
	int insertMemo(Memo m);
}
