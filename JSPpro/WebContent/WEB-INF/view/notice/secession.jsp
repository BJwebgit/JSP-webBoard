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
					<li><a href="/admin/notice/a-list">게시판</a></li> 
				</c:if>
				<c:if test="${!n.equals('firstID')}">
					<li><a href="main">메인</a></li>
					<li><a href="list">게시판</a></li> 
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
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px">
				<form action="secession" method="post">
					<h3 style="text-align: center">회 원 탈 퇴</h3>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20" />
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호 확인" name="checkPassword" maxlength="20" />
					</div>
					<input type="submit" class="btn btn-primary form-control" value="확 인" />
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="/js/bootstrap.js"></script>
</body>
</html>