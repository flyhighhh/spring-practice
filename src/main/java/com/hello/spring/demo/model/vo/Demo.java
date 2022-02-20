package com.hello.spring.demo.model.vo;

import java.sql.Date;
//util은 안됨..has a 방식?

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Demo {
	
	private int devNo;
	private String devName;
	private int devAge;
	private String devEmail;
	private String devGender;
	private String[] devLang;
	//private String devLang;
	private Date birthday;

}
