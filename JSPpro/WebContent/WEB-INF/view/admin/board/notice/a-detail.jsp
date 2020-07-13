<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar", "\n"); %><!DOCTYPE html>
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
			<a href="a-main" class="navbar-brand">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="a-main">메인</a></li>
				<li class="active"><a href="a-list">게시판</a></li> 
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/notice/pwdchange">비밀번호 변경</a></li>
						<li><a href="/notice/secession">회원탈퇴</a></li>
						<li><a href="/notice/logout">로그아웃</a></li>
					</ul>
				</li>
			</ul>	
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" style="background-color: #eeeeee; text-align: center;">글보기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%">글 제목</td>
						<td colspan="2">${n.title }</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2">${n.writerId }</td>
					</tr>
					<tr>
						<td>작성일자</td>
						<td colspan="2"><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${n.regdate }"/></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td colspan="2" style="text-align:left; text-indent:10px">
							<a download href="/upload/${n.files}" style="${style}">${fn:toUpperCase(n.files)}</a>
						</td>
					</tr>
					<tr>
						<td>글 내용</td>
						<td colspan="2" style="min-height: 200px; text-align: left;">${fn:replace(n.content, replaceChar, "<br/>") }</td>
					</tr>
				</tbody>
			</table>
			<c:if test="${n.writerId == userID }">
				<a href="a-list" class="btn btn-primary">목록</a>
				<a href="/notice/update-board?id=${n.id }" class="btn btn-primary">수정</a>
				<a href="/notice/delete-board?id=${n.id }" class="btn btn-primary" onclick="return confirm('삭제하시겠습니까?')">삭제</a>
			</c:if>
			<c:if test="${n.writerId != userID }">
				<a href="a-list" class="btn btn-primary">목록</a>
			</c:if>
		</div>
	</div>
	<p></p>
	<div class="container">
		<form action="a-detail" method="post">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2" style="background-color: #eeeeee; text-align: center;">댓 글</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="vertical-align:middle;">댓글 내용</td>
						<input type="hidden" name="id" value="${id}"/>
						<td><input type="text" class="form-control" placeholder="댓글을 입력하세요" name="comment" maxlength="50"/></td>
					</tr>
				</tbody>
			</table>
			<input type="submit" class="btn btn-primary pull-right" value="등록"/>
		</form>
	</div>
	<p></p>
	<div class="container">
		<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
		</thead>
		<tbody>
			<c:forEach var="n" items="${list}">
			<tr>
				<td style="vertical-align:middle;">${n.writerId }</td>
				<td><input type="text" class="form-control" value="${n.cmt }" readonly /></td>
				<c:if test="${n.writerId == userID }">
					<td style="width: 70px;"><a href="/notice/cmtdelete-board?cmtid=${n.id }&id=${id}" class="btn btn-primary pull-right" onclick="return confirm('삭제하시겠습니까?')">삭제</a></td>
				</c:if>
				<c:if test="${n.writerId != userID }">
					<td style="width: 70px;"></td>
				</c:if>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="/js/bootstrap.js"></script>
</body>
</html>