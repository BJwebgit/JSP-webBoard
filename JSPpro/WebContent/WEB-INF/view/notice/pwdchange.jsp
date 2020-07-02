<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="../css/bootstrap.css">
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
			<a href="main" class="navbar-brand">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main">메인</a></li>
				<li><a href="list">게시판</a></li> 
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="pwdchange">비밀번호 변경</a></li>
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
				<form action="pwdchange" method="post">
					<h3 style="text-align: center">비밀번호 변경</h3>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="현재비밀번호" name="userPassword" maxlength="20" />
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="새 비밀번호" name="nextPassword" maxlength="20" />
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="새 비밀번호 확인" name="checkPassword" maxlength="20" />
					</div>
					<input type="submit" class="btn btn-primary form-control" value="확 인" />
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
</body>
</html>