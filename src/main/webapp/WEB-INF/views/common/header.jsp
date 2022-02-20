<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloSpring</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link href="${path }/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container"></div>
		<header>
			<div id="header-container">		
				<h2>${param.title }</h2>
			</div>
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<a class="navbar-brand" href="#">
					<img src="${path }/resources/images/logo-spring.png" 
					alt="스프링로고" width="50px">
				</a>
				<button class="navbar-toggler" type="button"
				data-toggle="collapse" data-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item active">
							<a class="nav-link" href="${path }">HOME</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${path }/board/boardList.do">BOARD</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${path }/memo/memoList.do">MEMO</a>
						</li>
						<%-- <li class="nav-item">
							<a class="nav-link" href="${path }/demo/demo.do">demo controller실습</a>
						</li> --%>
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
							id="navbarDropdown" role="button"
							data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">
							DEMO
							</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="${path }/demo/demo.do">Demo</a>
								<a class="dropdown-item" href="${path }/demo/demoList.do">demoList</a>
								<a class="dropdown-item" href="${path }/demo/demoListMap.do">demoListMap</a>
							</div>
						</li>
					</ul>
					<c:if test="${loginMember==null }">
					<button class="btn btn-outline-success my-2 my-sm-0" type="button" 
						data-toggle="modal" data-target="#loginModal">로그인</button>
					<button class="btn btn-outline-success my-2 my-sm-0" type="button" 
						data-toggle="modal" onclick="location.assign('${path}/member/enrollMember.do')">회원가입</button>
					</c:if>
					<c:if test="${loginMember!=null }">
						<a href=""><c:out value="${loginMember.userName }" /></a>님, 환영합니다.
						<button class="btn btn-outline-success my-2 my-sm-0" type="button"
						onclick="location.replace('${path}/member/logout.do')">로그아웃</button>
					</c:if>
				</div>
			</nav>
		</header>
		
		<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
     		 aria-labelledby="exampleModalLabel" aria-hidden="true">
         <div class="modal-dialog" role="document">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLebel">로그인</h5>
                  <button type="button" class="close"  data-dismiss="modal" aria-label="close">
                     <span aria-hidden="true">&times;</span>
                  </button>
               </div>
               <form action="${path }/member/login.do" method="post">
               <div class="modal-body">
                  <input type="text" name="userId" class="form-control" 
                  placeholder="아이디입력" required><br>
                  <input type="password" name="password"  class="form-control"  placeholder="비밀번호입력"
                  required>
               </div>
               <div class="modal-footer">
                  <button type="submit" class="btn btn-outline-success">로그인</button>
                  <button type="button" class="btn btn-outline-success" 
                  data-dismiss="modal">취소</button>
               </div>   
               </form>
            </div>
            
         </div>
      </div>
		
		
		
		
		
		
		
		
		
		
		
		
		