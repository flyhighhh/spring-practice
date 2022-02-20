package com.hello.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hello.spring.board.model.service.BoardService;
import com.hello.spring.board.model.vo.Attachment;
import com.hello.spring.board.model.vo.Board;
import com.hello.spring.common.PageFactory;
import com.hello.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService service;

//그냥 불러오기	
//	@RequestMapping("/boardList.do")
//	public String selectBoardList(Model model) {
//		List<Board> boardList=service.selectBoardList();
//		model.addAttribute("boardList",boardList);
//		return "board/boardList";
//	}
	
	@RequestMapping("/boardList.do")
	public ModelAndView selectBoardList(
			@RequestParam(value="cPage", defaultValue="1") int cPage,
			@RequestParam(value="numPerPage", defaultValue="10") int numPerPage,
			ModelAndView mv)  {
		//cPage가 null이면 안돼... headerd에서 그냥 주소값만으로 넘어오니까 cPage는 안가져옴 그래서 @Request 써주기
		List<Board> boardList=service.selectBoardList(cPage, numPerPage);
		
		//페이징
		int totalData=service.selectBoardCount();
		mv.addObject("totalContents",totalData);
		mv.addObject("pageBar", PageFactory.getPageBar(totalData, cPage, numPerPage, 5, "boardList.do"));
		
		
		mv.addObject("boardList",boardList);
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@RequestMapping("/boardContent.do")
	public String boardContent(Board b, Model model) {
		model.addAttribute("board",b);
		return "board/boardContent";
	}
	
	
	@RequestMapping("/boardWrite.do")
	public String boardWrite() {
		return "board/boardWrite";
	}

	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST)
	public ModelAndView insertBoard(Board b, String writer, ModelAndView mv,
			@RequestParam(value="upFile", required=false) MultipartFile[] upFile, HttpServletRequest req) {
		
		log.debug(upFile[0].getOriginalFilename());
		log.debug("{}", upFile[0].getSize());
		log.debug(upFile[1].getOriginalFilename());
		log.debug("{}", upFile[1].getSize());
		
		
		b.setBoardWriter(new Member());
		b.getBoardWriter().setUserId(writer);
		
		
		//전달된 파일 업로드 처리하기
		//1. 저장경로 불러오기
		String path=req.getServletContext().getRealPath("/resources/upload/board/");
		File f=new File(path);
		if(!f.exists()) f.mkdirs();	//폴더가 존재하지 않으면 생성해라 (mkdirs: 폴더생성)
		
		b.setFiles(new ArrayList<Attachment>());
			//files가 0이라서 리스트 만들어서 넣어준거 저장공간 이용하려고
			//그래서 try문 안에 b.getFiles()에서 add해서 쓸 수 있음
		
		for(MultipartFile mf: upFile) {
			//if(!upFile[0].isEmpty()) {
			if(!mf.isEmpty()) {
				//파일 리네임처리를 직접처리
				//String originalFileName=upFile[0].getOriginalFilename();
				String originalFileName=mf.getOriginalFilename();
				String ext=originalFileName.substring(originalFileName.lastIndexOf("."));
				
				//리네임규칙설정
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmsssss");
				int rndNum=(int)(Math.random()*1000);
				String renameFile=sdf.format(System.currentTimeMillis())+"_"+rndNum+ext;
				
				//리네임명으로 파일저장하기
				//multipartFile클래스에서 파일을 저장하는 매소드를 제공함. -> transferTo(파일객체);
				try {
					//upFile[0].transferTo(new File(path+renameFile));
					mf.transferTo(new File(path+renameFile));
					Attachment file=new Attachment();
					file.setOriginalFilename(originalFileName);
					file.setRenamedFilename(renameFile);
					b.getFiles().add(file);
					
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		log.debug("boardData: {}", b);
		String msg="";
		String loc="";
		try {
			int result=service.insertBoard(b);
			msg="게시글 등록 성공";
			loc="/board/boardList.do";
		}catch(RuntimeException e) {
			msg="게시글 등록 실패 :" +e.getMessage();
			loc="/board/boardWrite.do";
		}
		
//		if(result>0) {
//			msg="게시글 등록 성공";
//			loc="/board/boardList.do";
//		}else {
//			msg="게시글 등록 실패";
//			loc="/board/boardWrite.do";
//		}
		
		mv.addObject("msg",msg);
		mv.addObject("loc",loc);
		mv.setViewName("common/msg");
		return mv;
	}
	
	@RequestMapping("/boardView.do")
	public ModelAndView boardView(int boardNo, ModelAndView mv) {
		mv.addObject("board",service.selectBoardOne(boardNo));
		//mv.setViewName();
		return mv;
	}
	
	@RequestMapping("download.do")
	public void fileDownload(String oriName, String reName,
			HttpServletRequest req, HttpServletResponse res,
			@RequestHeader(value="User-agent") String header) {
		String path=req.getServletContext().getRealPath("/resources/upload/board/");
		File saveFile=new File(path+reName);
		BufferedInputStream bis=null;
		ServletOutputStream sos=null;
		try {
			bis=new BufferedInputStream(new FileInputStream(saveFile));
			sos=res.getOutputStream();
			boolean isMs=header.contains("Trident")||header.contains("MSIE");
			String encodeStr="";
			if(isMs) {
				encodeStr=URLEncoder.encode(oriName,"UTF-8");
				encodeStr=encodeStr.replaceAll("\\+", "%20");
			}else {
				encodeStr=new String(oriName.getBytes("UTF-8"),"ISO-8859-1");
			}
			res.setContentType("application/octet-stream;charset=utf-8");
			res.setHeader("Content-Disposition", "attachment;filename=\""+encodeStr+"\"");
			int read=-1;
			while((read=bis.read())!=-1) {
				sos.write(read);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bis.close();
				sos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
}
