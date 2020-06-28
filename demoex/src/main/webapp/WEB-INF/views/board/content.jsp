<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 내용</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"  rel="stylesheet"/>
</head>

<body>
	<div class="container">
		글번호: ${obj.brd_no}<br />
		글제목: ${obj.brd_title}<br />
		글내용: ${fn:replace(obj.brd_content, newLineChar, "<br />")}<br />
		작성자: ${obj.brd_id}<br />
		조회수: ${obj.brd_hit}<br />
		날짜  : ${obj.brd_date}<br />
		<img src="${pageContext.request.contextPath}/board/getimg?no=${obj.brd_no}" width="100px" height="100px"/><br />
		<hr />
		<a href="${pageContext.request.contextPath}/board/list" class="btn btn-success">목록</a>
		<a href="${pageContext.request.contextPath}/board/update?no=${obj.brd_no}" class="btn btn-success">수정</a>
		<a href="#" class="btn btn-success mydeletebtn">삭제</a>
		
		<c:if test="${prev !=0 }">
		<a href="${pageContext.request.contextPath}/board/content?no=${prev}" class="btn btn-success">이전글</a>
		</c:if>
		
		<c:if test="${next !=0 }">
		<a href="${pageContext.request.contextPath}/board/content?no=${next}" class="btn btn-success">다음글</a>
		</c:if>
	</div>
	
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script type="text/javascript">
		$(function(){
			$('.mydeletebtn').click(function(){
				var idx = $(this).index('.mydeletebtn');
				var no = '${obj.brd_no}';

				Swal.fire({
					  title: '삭제 확인',
					  text: "삭제하시겠습니까?",
					  icon: 'warning',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: 'Yes, delete it!',
					  cancelButtonText: '취소'
					}).then((result) => {
					  if (result.value) {
					    window.location.href= "/board/delete?no=" + no;
					  }
					})
				})
			})
	</script>

</body>
</html>