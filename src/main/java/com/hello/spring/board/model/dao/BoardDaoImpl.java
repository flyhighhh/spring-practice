package com.hello.spring.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.hello.spring.board.model.vo.Attachment;
import com.hello.spring.board.model.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Override
	public List<Board> selectBoardList(SqlSessionTemplate session, int cPage, int numPerpage) {
		return session.selectList("board.selectBoardList",null,
				new RowBounds((cPage-1)*numPerpage,numPerpage));
	}

	@Override
	public int insertBoard(SqlSessionTemplate session, Board b) {
		return session.insert("board.insertBoard",b);
	}
	@Override
	public int insertAttachment(SqlSessionTemplate session, Attachment file) {
		return session.insert("board.insertAttachment", file);
	}
	
	@Override
	public int selectBoardCount(SqlSessionTemplate session) {
		return session.selectOne("board.selectBoardCount");
	}

	@Override
	public Board selectBoardOne(SqlSessionTemplate session, int boardNo) {
		return session.selectOne("board.selectBoardOne", boardNo);
	}
	
	
}
