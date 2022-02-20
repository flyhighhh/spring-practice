package com.hello.spring.board.model.vo;

import java.sql.Date;
import java.util.List;

import com.hello.spring.member.model.vo.Member;

import lombok.Data;

@Data
public class Board {
	
	 private int boardNo;
	 private String boardTitle;
	 private Member boardWriter;
	 private String boardContent;
	 private Date boardDate;
	 private int boardReadCount;
	 private List<Attachment> files;
	 
}