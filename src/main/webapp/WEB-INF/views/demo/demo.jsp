<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="demo테스트" />
</jsp:include>
<style>
div#demo-container{
		width:50%;
		padding:15px;
		margin:0 auto;
		border:1px solid lightgray;
		border-radius:10px;
	}
</style>
<section id="container">
	<div id="demo-container">
		<h2>Parameter Test</h2>
		<form id="devFrm" method="post">
			<div class="form-group row">
				<label for="devName" class="col-sm-2 col-form-label">이름</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="devName" name="devName">
				</div>
			</div>
			<div class="form-group row">
				<label for="devAge" class="col-sm-2 col-form-label">나이</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="devAge" name="devAge">
				</div>
			</div>
			<div class="form-group row">
				<label for="devEmail" class="col-sm-2 col-form-label">이메일</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="devEmail"
						name="devEmail">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">성별</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="devGender"
							id="devGender0" value="M"> <label
							class="form-check-label" for="devGender0">남</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="devGender"
							id="devGender1" value="F"> <label
							class="form-check-label" for="devGender1">여</label>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">개발언어</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="devLang"
							id="devLang0" value="Java"> <label
							class="form-check-label" for="devLang0">Java</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="devLang"
							id="devLang1" value="C"> <label class="form-check-label"
							for="devLang1">C</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="devLang"
							id="devLang2" value="Javascript"> <label
							class="form-check-label" for="devLang2">Javascript</label>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">생일</label>
				<div class="col-sm-10">
					<input type="date" name="birthday">
				</div>
			</div>
		</form>
		<div class="list-group">
			<button type="button" class="list-group-item list-group-item-action"
				onclick="requestSend('/demo/demo1.do')">
				Servlet처럼 parameter 처리하기(HttpServletRequest를 이용)
			</button>
			<button type="button" class="list-group-item list-group-item-action"
				onclick="requestSend('/demo/demo2.do')">
				@RequestParam이용해서 파라미터처리하기
			</button>
			<button type="button" class="list-group-item list-group-item-action"
				onclick="requestSend('/demo/autoParam.do')">
				autoParam이용해서 파라미터처리하기
			</button>
			<button type="button" class="list-group-item list-group-item-action"
				onclick="requestSend('/demo/demo3.do')">
				Command 객체로 파라미터처리하기
			</button>
			<button type="button" class="list-group-item list-group-item-action"
				onclick="requestSend('/demo/demo3.do')">
				Map으로 파라미터처리하기
			</button>
			<button type="button" class="list-group-item list-group-item-action"
				onclick="requestSend('/demo/insertDemo.do')">
				insertDemo실행
			</button>
		</div>
	</div>
	<script>
		const requestSend=(path)=>{
			$("#devFrm").attr("action","${path}"+path);
			$("#devFrm").submit();
		}
	</script>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />