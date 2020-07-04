<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.io.PrintWriter" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="../css/bootstrap.css">
<title>JSP 게시판</title>
</head>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String)session.getAttribute("userID");
		}
	%>
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
				<li class="active"><a href="list">게시판</a></li> 
			</ul>
			<%
				if(userID == null){
			%>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">접속하기<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="login">로그인</a></li>
							<li><a href="join">회원가입</a></li>
						</ul>
					</li>
				</ul>	
			<%
				} else {
			%>	
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
			<%
				}
			%>
		</div>
	</nav>
	<div class="container">
		<div class="pull-right">
			<h3 class="hidden">공지사항 검색폼</h3>
			<form class="table-form">
				<fieldset>
					<legend class="hidden">공지사항 검색 필드</legend>
					<label class="hidden">검색분류</label>
					<select name="f">
						<option ${(param.f == "title")?"selected":"" } value="title">제목</option>
						<option ${(param.f == "writer_id")?"selected":"" } value="writer_id">작성자</option>
					</select> 
					<label class="hidden">검색어</label>
					<input type="text" name="q" value="${param.q}"/>
					<input class="btn btn-search" type="submit" value="검색" />
				</fieldset>
			</form>
		</div>
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="n" items="${list}">
					<tr>
						<td>${n.id }</td>
						<td class="title indent text-align-left"><a href="detail?id=${n.id }">${n.title }</a></td>
						<td>${n.writerId }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${n.regdate }"/></td>		
					</tr>	
					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:set var="page" value="${(empty param.p)?1:param.p}"/>
			<c:set var="startNum" value="${page-(page-1)%5}" />
			<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10), '.') }" />
			
			<div class="indexer margin-top align-right">
				<h3 class="hidden">현재 페이지</h3>
				<div class="pull-right"><span class="text-orange text-strong">${(empty param.p)?1:param.p }</span> / ${lastNum} pages</div>
			</div>

			<div class="margin-top align-center pager">	
				<div style="display: inline-block;">		
					<c:if test="${startNum > 1}">
						<a href="?p=${startNum-1}&t=&q=" class="btn btn-prev">이전</a>
					</c:if>
					<c:if test="${startNum <= 1}">
						<span class="btn btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</span>
					</c:if>	
				</div>
					<ul class="-list- center" style="display: inline-block; padding:0;">
					<c:forEach var="i" begin="0" end="4">
					<c:if test="${(startNum+i) <= lastNum}">
						<li><a class="-text- ${(page == (startNum+i))?'orange':''} bold" href="?p=${startNum+i}&f=${param.f}&q=${param.q}" >${startNum+i}</a></li>
					</c:if>
					</c:forEach>			
					</ul>
				<div style="display: inline-block;">
					<c:if test="${startNum+4 < lastNum}">
						<a href="?p=${startNum+5}&t=&q=" class="btn btn-next">다음</a>
					</c:if>
					<c:if test="${startNum+4 >= lastNum }">	
						<span class="btn btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</span>
					</c:if>
					
				</div>
			</div>
			<a href="write" class="btn btn-primary pull-right">글쓰기</a>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
</body>
</html>