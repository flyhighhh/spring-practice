<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<section>
<style>
    div#memo-container{width:60%; margin:0 auto;}
</style>
    <div id="memo-container">
        <form action="${path }/memo/insertMemo.do" class="form-inline">
            <input type="text" class="form-control col-sm-6" name="memo" placeholder="메모" required/>&nbsp;
            <input type="password" class="form-control col-sm-2" name="password" maxlength="4" placeholder="비밀번호" required/>&nbsp;
            <button class="btn btn-outline-success" type="submit" >저장</button>
        </form>
    </div>

<!-- 입력하는것까지 만들기 -->

 <br />
        <!-- 메모목록 -->
        <table class="table">
            <tr>
                <th scope="col">번호</th>
                <th scope="col">메모</th>
                <th scope="col">날짜</th>
                <th scope="col">삭제</th>
            </tr>
            <c:forEach items="${list}" var="m">
			<tr>
	            <td><c:out value="${m.memoNo }"/></td>
	            <td><c:out value="${m.memo }"/></td>
	            <td><c:out value="${m.memoDate }"/></td>
	            <td><input type="button" value="삭제" onclick=""></td>
            </tr>
            </c:forEach>
        </table>

</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>