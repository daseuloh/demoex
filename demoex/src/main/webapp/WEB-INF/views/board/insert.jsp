<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" 
		rel="stylesheet"/>
</head>

<body>
	<div class="container">
		<div style="width:900px; padding:20px; border:1px solid #cccccc;">
			<form action="/board/insert" method="post" enctype="multipart/form-data">
				<div class="form-inline" style="margin:5px;">
					<label style="width:100px;">글제목</label>
					<input style="width:700px" type="text" class="form-control" name="brd_title" placeholder="제목"/>
				</div>
				<div class="form-inline" style="margin:5px;">
					<label style="width:100px;">내용</label>
					<textarea style="width:700px" class="form-control" name="brd_content" placeholder="내용"></textarea>
				</div>
				<div class="form-inline" style="margin:5px;">
					<label style="width:100px;">작성자</label>
					<input style="width:700px" type="text" class="form-control" name="brd_id" value="${userid}" readonly/>
				</div>
				<div class="form-inline" style="margin:5px;">
					<label style="width:100px;">이미지</label>
					<input style="width:700px" type="file" class="form-control" name="imgs"/>
				</div>
				<div class="form-inline" style="margin:5px;">
					<label style="width:100px;"></label>
					<input type="submit" class="btn btn-success" value="글쓰기" style="margin-left:700px"/>

				</div>
				
			</form>
		</div>
			
	</div>
	
</body>
</html>