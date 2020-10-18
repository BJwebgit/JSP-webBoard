JSP-webBoard
============

JSP를 이용해서 구현하였고, Oracle을 이용한 회원관리 게시판기능이 있는 반응형 웹사이트입니다.

![Alt text](/JSPpro/WebContent/images/JSP-webBoard사진.png)

컴포넌트 구조 설명
----------
[java]
* Controller
  * member
    * ListController.java (일반 사용자에게 보여지는 메인화면 매핑 컨트롤러)
    * JoinController.java (회원가입 매핑을 수행하는 컨트롤러)
    * LoginController.java (로그인 매핑을 수행하는 컨트롤러)
    * LogoutController.java (로그아웃 매핑을 수행하는 컨트롤러)
    * PwdChangeController.java (패스워드 매핑을 처리하는 컨트롤러)
    * SecessionController.java (회원탈퇴 매핑을 처리하는 컨트롤러)
  * admin
    * AdminListController.java (관리자에게 보여지는 메인화면 컨트롤러)
    * AdminBoardListController.java (관리자 게시판 컨트롤러)
    * AdminDetailController.java (관리자 게시판상세보기 컨트롤러)
* Service
  * member
    * JoinService.java (회원가입 처리기능을 수행하는 Service)
    * LoginService.java (로그인 처리기능을 수행하는 Service)
    * SecessionService.java (회원탈퇴 처리기능을 수행하는 Service)
  * board
    * BoardService.java (게시판과 관련된 기능을 수행하는 Serivce)
    * BoardCmtService.java ((게시판)댓글 관련된 기능을 수행하는 Serivce)
* Domain
    * Join.java (데이터베이스 레코드의 데이터를 매핑하기위한 Member데이터 객체)
    * Board.java (데이터베이스 레코드의 데이터를 매핑하기위한 Board데이터 객체)
    * BoardCmt.java (데이터베이스 레코드의 데이터를 매핑하기위한 BoardCmt(댓글)데이터 객체)
* filter
    * CharacterEncodingFilter.java (페이지 인코딩을 수행하는 컨트롤러)
