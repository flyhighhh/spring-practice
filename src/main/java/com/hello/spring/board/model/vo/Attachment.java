package com.hello.spring.board.model.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Attachment {
	private int attachmentNo;
	private int boardNo;
	private String originalFilename;
	private String renamedFilename;
	private Date uploadDate;
	private int downloadCount;
	
}
