package com.hello.spring.board.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.hello.spring.board.model.dao.BoardDao;
import com.hello.spring.board.model.vo.Attachment;
import com.hello.spring.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao dao;
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<Board> selectBoardList(int cPage, int numPerPage){
		return dao.selectBoardList(session, cPage, numPerPage);
	}

	@Override
	//@Transactional
	//(isolation = Isolation.READ_COMMITTED)
	public int insertBoard(Board b) throws RuntimeException {
		log.debug("전 boardNo: {}", b.getBoardNo());
		int result=dao.insertBoard(session, b);
		log.debug("후 boardNo: {}", b.getBoardNo());
		if(result>0 && !b.getFiles().isEmpty()) {	//b.getFiles().size()>0
			
			try {
				for(Attachment a : b.getFiles()) {
				//b.getFiles().get(0).setBoardNo(b.getBoardNo());
				//Attachment a=b.getFiles().get(0);
					a.setBoardNo(b.getBoardNo());
				//result=dao.insertAttachment(session, b.getFiles().get(0));
					result=dao.insertAttachment(session, a);
				
				//if spring 트렌젝션 매니저가 담당을 함
				}
			}catch(RuntimeException e) {
				throw new RuntimeException("기본에러! 등록실패");
			}
		}
		return result;
	}

	@Override
	public int selectBoardCount() {
		return dao.selectBoardCount(session);
	}

	@Override
	public Board selectBoardOne(int boardNo) {
		return dao.selectBoardOne(session, boardNo);
	}
	
	
}
