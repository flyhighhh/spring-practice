<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="헬로우월드"/>
</jsp:include>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

	<section id="content">
		<h3><a href="${path }/demo/demo5.do">추가 파라미터 </a></h3>
		<h3><a href="${path }/demo/demo6.do">추가 ResponseBody </a></h3>
		<h3><a href="javascript:fn_chatting();">채팅하기</a></h3>
		<h3><a href="javascript:fn_mapperTest();">mapperTest</a></h3>
	</section>
	
	<script type="text/javascript">
		const fn_chatting=()=>{
			open("${path }/chatting/connectChatting","_blank","width=300,height=500");
			
		}
		
		const fn_mapperTest=()=>{
			$.get("${path}/mapperTest.do?userId=sunny",data=>{
				console.log(data);
			});
		}
	</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>