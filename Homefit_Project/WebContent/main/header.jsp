<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈핏</title>
<style>
#sootag{
	margin: 0 0px 4px 13px;
}
#joinbom{
	margin: 0;  padding: 0;
}
#logo_img {
	width: 106px;
    margin-bottom: 3px;
}
</style>
</head>
<body>
	<!--  header  -->
	<header class="header_area">
	    <div class="main_menu">
	      <nav class="navbar navbar-expand-lg navbar-light">
	        <div class="container">
	          <a class="navbar-brand logo_h" href="../main/main.do"><img src="../img/logo.png" id="logo_img"></a>
	          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
	            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	          	<span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
	            <ul class="nav navbar-nav menu_nav ml-auto mr-auto">
	              <li class="nav-item submenu dropdown">
	                 <a href="../ex/total.do" class="nav-link dropdown-toggle">홈트</a>
	              </li>
	              <li class="nav-item submenu dropdown">
	                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
	                  aria-expanded="false">홈트샵</a>
	                <ul class="dropdown-menu">
	                <!-- category.html -->
	                  <li class="nav-item"><a class="nav-link" href="../shop/shop.do?cate_no=1">식단</a></li>
	                  <li class="nav-item"><a class="nav-link" href="../shop/shop.do?cate_no=2">건강간식</a></li>
	                  <li class="nav-item"><a class="nav-link" href="../shop/shop.do?cate_no=3">식사대용</a></li>
	                  <li class="nav-item"><a class="nav-link" href="../shop/shop.do?cate_no=4">단백질보충</a></li>
	                  <li class="nav-item"><a class="nav-link" href="../shop/shop.do?cate_no=5">체지방관리</a></li>
	                  <li class="nav-item"><a class="nav-link" href="../shop/shop.do?cate_no=6">헬스케어</a></li>
	                </ul>
				  </li>
	              <li class="nav-item submenu dropdown">
	                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
	                  aria-expanded="false">코치</a>
	                <ul class="dropdown-menu">
	                  <li class="nav-item"><a class="nav-link" href="../coach/coachlist.do">코치소개</a></li>
	                  <li class="nav-item"><a class="nav-link" href="../coachreserve/reserve.do">코치스케쥴예약</a></li>
	                  <li class="nav-item"><a class="nav-link" href="../coach/qna.do">Q&N</a></li>
	                </ul>
				</li>
				<li class="nav-item submenu dropdown">
	                <a href="../challenge/Challenge.do" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
	                  aria-expanded="false">함께도전해요</a>
	                <ul class="dropdown-menu">
	                <li class="nav-item"><a class="nav-link" href="../challenge/list.do">전체도전목록</a></li>
	                  <li class="nav-item"><a class="nav-link" href="../challenge/Challenge.do">식단챌린지</a></li>
	                  <li class="nav-item"><a class="nav-link" href="../challenge/mychallenge.do">마이도전</a></li>
	                  <li class="nav-item"><a class="nav-link" href="tracking-order.html">기타</a></li>
	                </ul>
	             </li>
	                <li class="nav-item submenu dropdown">
	                 <a href="../news/news.do" class="nav-link dropdown-toggle">홈트 뉴스</a>
	              </li>
	            </ul>
	
	             <ul class="nav-shop" id="nav-user">
					<c:if test="${sessionScope.id==null }">
	           			<li class="nav-item"><button><a class="#" href="../member/login.do">로그인</a></button></li>
	              	 	<li class="nav-item">/</li>
	              		<li class="nav-item" id="joinbom"><button><a class="#" href="../member/join.do">회원가입</a></button></li>
	          		</c:if>
	          		<c:if test="${sessionScope.id!=null }">
	          			<li class="nav-item"> ${sessionScope.name } 님</li>
	          			<li class="nav-item">/</li>
	           			<li class="nav-item"><button><a class="#" href="../member/logout.do">로그아웃</a></button></li>
	              		<li class="nav-item"><button><a href="../shop/wishlist.do"><i class="ti-shopping-cart"></i><span class="nav-shop__circle">1</span></button></a></li>
	          			<li class="nav-item" id="sootag"><button><a  href="../mypage/mytest.do"><img src="../main/user.png" width=20 height=20></a></button></li>
	          		</c:if>
	            </ul>
	          </div>
	        </div>
	      </nav>
	    </div>
  </header>
</body>
</html>