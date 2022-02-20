package com.hello.spring.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.hello.spring.board.model.vo.Attachment;
import com.hello.spring.board.model.vo.Board;
import com.hello.spring.member.model.vo.Member;

@Mapper
public interface MemberMapper {
	//멤버조회할 수 있는 mapper
	//실행할 선언부를 써주면 됨
	
	@Select("SELECT * FROM MEMBER WHERE USERID=#{userId}")
	
	@Results(id="memberMap", value= {
			@Result(column="hobby", property="hobby", typeHandler=com.hello.spring.common.StringArrayTypeHandler.class)
			//xml의 resultMap해준 부분이라고 생각해
	})
	Member selectMember(String userId);
	
	
	@Select("SELECT * FROM MEMBER")
	@ResultMap("memberMap")
	List<Member> selectMemberList();
	
	
	@Select("SELECT * FROM BOARD WHERE BOARDNO=#{boardNo}")
	@Results(value= {
			@Result(column="boardWriter", property="boardWriter", one=@One(select="selectMember")),
				//다른 셀렉문 가져오는거 one은 1:1 many 1:다
			@Result(column="boardNo", property="files", many=@Many(select="selectAttachment"))
	})
	Board selectBoard(int boardNo);

	@Select("SELECT * FROM ATTACHMENT WHERE BOARDNO=#{boardNo}")
	Attachment selectAttachment(int boardNo);
	
}
