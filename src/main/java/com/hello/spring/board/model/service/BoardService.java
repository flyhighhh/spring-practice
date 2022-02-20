package com.hello.spring.board.model.service;

import java.util.List;

import com.hello.spring.board.model.vo.Board;

public interface BoardService {
	
	List<Board> selectBoardList(int cPage, int numPerPage);
	
	int insertBoard(Board b);
	
	int selectBoardCount();
	
	Board selectBoardOne(int boardNo);
}
