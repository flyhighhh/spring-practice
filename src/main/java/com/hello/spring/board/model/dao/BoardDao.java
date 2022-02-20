package com.hello.spring.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.hello.spring.board.model.vo.Attachment;
import com.hello.spring.board.model.vo.Board;

public interface BoardDao {
	
	List<Board> selectBoardList(SqlSessionTemplate session, int cPage, int numPerPage);
	
	int insertBoard(SqlSessionTemplate session, Board b);
	
	int selectBoardCount(SqlSessionTemplate session);
	
	int insertAttachment(SqlSessionTemplate session, Attachment file);
	
	Board selectBoardOne(SqlSessionTemplate session, int boardNo);

}
