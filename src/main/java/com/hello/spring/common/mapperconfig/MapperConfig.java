package com.hello.spring.common.mapperconfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hello.spring.memo.model.vo.Memo;

@Configuration
@MapperScan("com.hello.spring.common.mapper")
	//-> mapper들을 조회할 수 있는 어노테이션 (이 주소로 인터페이스랑 연결)
public class MapperConfig {

	@Bean
	public Memo memo() {
		return new Memo();
	}

}
