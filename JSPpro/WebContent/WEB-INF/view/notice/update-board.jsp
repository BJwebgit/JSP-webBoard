<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="/css/bootstrap.css">
<title>JSP 게시판</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<c:if test="${n.equals('firstID')}">
				<a href="/admin/notice/a-main" class="navbar-brand">JSP 게시판 웹 사이트</a>
			</c:if>
			<c:if test="${!n.equals('firstID')}">
				<a href="main" class="navbar-brand">JSP 게시판 웹 사이트</a>
			</c:if>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<c:if test="${n.equals('firstID')}">
					<li><a href="/admin/notice/a-main">메인</a></li>
					<li class="active"><a href="/admin/notice/a-list">게시판</a></li> 
				</c:if>
				<c:if test="${!n.equals('firstID')}">
					<li><a href="main">메인</a></li>
					<li class="active"><a href="list">게시판</a></li> 
				</c:if>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="pwdchange">비밀번호 변경</a></li>
						<li><a href="secession">회원탈퇴</a></li>
						<li><a href="logout">로그아웃</a></li>
					</ul>
				</li>
			</ul>	
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<form action="update-board?id=${id }" method="post" enctype="multipart/form-data">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2" style="background-color: #eeeeee; text-align: center;">글쓰기 양식</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>제목</th>
							<td><input type="text" class="form-control" value="${n.title }" name="title" maxlength="50"/></td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td><input type="file" name="file"/></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea class="form-control" name="content" maxlength="2048" style="height: 350px;">${n.content }</textarea></td>
						</tr>
						<tr>
                            <td colspan="4"><input type="checkbox" id="open" name="open" value="true"><label for="open">공개</label> </td>
                        </tr>
					</tbody>
				</table>
				<input type="submit" class="btn btn-primary pull-right" value="글쓰기"/>
			</form>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="/js/bootstrap.js"></script>
</body>
</html>