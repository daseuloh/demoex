<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" 
		rel="stylesheet"/>
</head>

<body>
	<div class="container">
		<div class="form-inline">
			<div style="margin-right:30px">
				<a href="${pageContext.request.contextPath}/board/insert" class="btn btn-success">글쓰기</a>
			</div>
			
			<form action="${pageContext.request.contextPath}/board/list" method="get">
				<input type="hidden" name="page" value="1"/>
				<input type="text" name="text" class="form-control" placeholder="검색어 입력"/>
				<input type="submit" class="btn btn-success" value="검색"/>
			</form>
		</div>
		
		<table class="table table-sm">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>날짜</th>
				</tr>
			</thead>
			
			<tbody>
				<c:if test="${empty list}">
				<tr>
					<td colspan="5">검색결과가 없습니다.</td>
				</tr>
				</c:if>
				
				<c:if test="${!empty list}">
				<c:forEach var="tmp" items="${list}">
				<tr>
					<td>${tmp.brd_no}</td>
					<td>
						<a href="/board/content?no=${tmp.brd_no}">${tmp.brd_title}</a>
					</td>
					<td>${tmp.brd_id}</td>
					<td><fmt:formatNumber value="${tmp.brd_hit}" pattern="#,###"/></td>
					<td>
						<c:set var="dt" value="${fn:split(tmp.brd_date, ' ')}"/>
						${dt[0]}
					</td>
				</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
		
		<div id="pagination-div"></div>
		
	</div>
	
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script>
	
	<script>
		$(function(){
			$('#pagination-div').twbsPagination({
				totalPages: Number('${cnt}'), //그냥 값 : 콘트롤러의 모델로부터 전달 받은 값
				visiblePage: 10,
				startPage: Number('${param.page}'), //param.ㅇㅇ : 표시된 jsp에서 사용자로부터 입력된 값
				initiateStartPageClick: false,
				onPageClick: function (event, page){
						window.location.href="/board/list?page=" + page + "&text=${param.text}";
					}
				});
			})
	</script>
	
</body>
</html>