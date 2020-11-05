<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈핏</title>
<style type="text/css">
.main_title {
	font-size: 30px;
	text-align: center;
	margin-bottom: 22px;
}

.main_subtit {
	margin-top: 10px;
	font-size: 18px;
}

.main_bg {
	background-image: url(../theme/img/home/main-bg.jpg);
	background-repeat: no-repeat;
	width: 100%;
	height: 650px;
	background-position: center;
	background-size: cover;
}
.main_content {
	float: left;
    margin-top: 162px;
}
</style>
</head>
<body>
	<div class="main_bg">
		<div class="col-sm-7 col-lg-6 offset-lg-1 pl-4 pl-md-5 pl-lg-0 main_content">
			<div class="hero-banner__content">
				<h4>Shop is fun</h4>
				<h1>Browse Our Premium Product</h1>
				<p>Us which over of signs divide dominion deep fill bring
					they're meat beho upon own earth without morning over third. Their
					male dry. They are great appear whose land fly grass.</p>
				<a class="button button-hero" href="#">Browse Now</a>
			</div>
		</div>
	</div>
	<!-- 메인 1  -->
	<!-- <section class="hero-banner">
		<div class="container main_bg">
			<div class="row no-gutters align-items-center pt-60px">
				<div class="col-5 d-none d-sm-block">
					<div class="hero-banner__img"></div>
				</div>
				
			</div>
		</div>
	</section>
	  -->
	<!-- <section class="hero-banner">
		<div class="container">
			<div class="row no-gutters align-items-center pt-60px">
				<div class="col-5 d-none d-sm-block">
					<div class="hero-banner__img">
						<img class="img-fluid" src="../theme/img/home/hero-banner.png"
							alt="">
					</div>
				</div>
				<div class="col-sm-7 col-lg-6 offset-lg-1 pl-4 pl-md-5 pl-lg-0">
					<div class="hero-banner__content">
						<h4>Shop is fun</h4>
						<h1>Browse Our Premium Product</h1>
						<p>Us which over of signs divide dominion deep fill bring
							they're meat beho upon own earth without morning over third.
							Their male dry. They are great appear whose land fly grass.</p>
						<a class="button button-hero" href="#">Browse Now</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	 -->

	<!-- 메인 2 -->
	<!--  <section class="section-margin mt-0">
      <div class="owl-carousel owl-theme hero-carousel">
        <div class="hero-carousel__slide">
          <img src="../theme/img/home/hero-slide1.png" alt="" class="img-fluid">
          <a href="#" class="hero-carousel__slideOverlay">
            <h3>Wireless Headphone</h3>
            <p>Accessories Item</p>
          </a>
        </div>
        <div class="hero-carousel__slide">
          <img src="../theme/img/home/hero-slide2.png" alt="" class="img-fluid">
          <a href="#" class="hero-carousel__slideOverlay">
            <h3>Wireless Headphone</h3>
            <p>Accessories Item</p>
          </a>
        </div>
        <div class="hero-carousel__slide">
          <img src="../theme/img/home/hero-slide3.png" alt="" class="img-fluid">
          <a href="#" class="hero-carousel__slideOverlay">
            <h3>Wireless Headphone</h3>
            <p>Accessories Item</p>
          </a>
        </div>
      </div>
    </section> -->

	<!--  최근 방문한 샵  -->
	<section class="section-margin calc-60px">
		<div class="container">
			<div class="section-intro pb-60px">
				<h2 class="main_title">최근 본 홈트샵 상품</h2>
			</div>

			<div class="owl-carousel owl-theme" id="bestSellerCarousel">
				<c:forEach var="vo" items="${cList }" varStatus="s">
					<div class="card text-center card-product">
						<div class="card-product__img">
							<img class="img-fluid" src="${vo.poster }" alt="">
						</div>
						<div class="card-body">
							<h4 class="card-product__title">${vo.title }</a>
							</h4>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>


	<!-- 나의 운동 레벨별 출력  -->
	<section class="section-margin calc-60px">
		<div class="container">
			<div class="section-intro pb-60px">
				<h2 class="main_title">나의 레벨에 맞는 운동 추천</h2>
			</div>
			<div class="row">
				<c:forEach items="${list2 }" var="vo" begin="0" end="3">
					<div class="col-md-6 col-lg-4 col-xl-3">
						<div class="card text-center card-product">
							<div class="card-product__img">
								<a href="../ex/detail.do?home_no=${vo.evo.home_no }"> <img
									class="card-img" src="${vo.evo.poster }" alt="">
								</a>
								<!-- 이미지 호버부분  
                <ul class="card-product__imgOverlay">
                  <li><button><i class="ti-search"></i></button></li>
                  <li><button><i class="ti-shopping-cart"></i></button></li>
                  <li><button><i class="ti-heart"></i></button></li>
                </ul> -->
							</div>
							<div class="card-body">
								<h4 class="card-product__title main_subtit">
									<a href="single-product.html">${vo.evo.subject }</a>
								</h4>
							</div>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>
	</section>

	<!-- 나의 운동 카테고리 출력  -->
	<section class="section-margin calc-60px">
		<div class="container">
			<div class="section-intro pb-60px">
				<h2 class="main_title">내가 관심있는 운동 추천</h2>
			</div>
			<div class="row">
				<c:forEach items="${list1 }" var="vo" begin="0" end="3">
					<div class="col-md-6 col-lg-4 col-xl-3">
						<div class="card text-center card-product">
							<div class="card-product__img">
								<a href="../ex/detail.do?home_no=${vo.evo.home_no }"> <img
									class="card-img" src="${vo.evo.poster }" alt="">
								</a>
								<!-- 이미지 호버부분  
                <ul class="card-product__imgOverlay">
                  <li><button><i class="ti-search"></i></button></li>
                  <li><button><i class="ti-shopping-cart"></i></button></li>
                  <li><button><i class="ti-heart"></i></button></li>
                </ul> -->
							</div>
							<div class="card-body">
								<h4 class="card-product__title main_subtit">
									<a href="single-product.html">${vo.evo.subject }</a>
								</h4>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>




	<%-- 	
	<!-- 최근 방문한 샵  -->
	<div class="row" style="height: 500px;">
		<h2 class="main_title">최근 방문한 샵은?</h2>
		<c:forEach var="vo" items="${cList }" varStatus="s">
			<c:if test="${s.index<6 }">
				<div class="col-md-2">
					<div class="thumbnail">
						<img src="${vo.poster }" alt="Lights" style="width: 100%">
						<div class="caption">
							<p>${vo.title }</p>
						</div>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</div>
 	--%>




	<!-- ================ offer section start ================= -->
	<section class="offer" id="parallax-1" data-anchor-target="#parallax-1"
		data-300-top="background-position: 20px 30px"
		data-top-bottom="background-position: 0 20px">
		<div class="container">
			<div class="row">
				<div class="col-xl-5">
					<div class="offer__content text-center">
						<h3>Up To 50% Off</h3>
						<h4>Winter Sale</h4>
						<p>Him she'd let them sixth saw light</p>
						<a class="button button--active mt-3 mt-xl-4" href="#">Shop
							Now</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ================ offer section end ================= -->

	<!-- ================ Best Selling item  carousel ================= -->
	<!--  <section class="section-margin calc-60px">
      <div class="container">
        <div class="section-intro pb-60px">
          <p>Popular Item in the market</p>
          <h2>Best <span class="section-intro__style">Sellers</span></h2>
        </div>
        
        <div class="owl-carousel owl-theme" id="bestSellerCarousel">
        
          <div class="card text-center card-product">
            <div class="card-product__img">
              <img class="img-fluid" src="../theme/img/product/product1.png" alt="">
              <ul class="card-product__imgOverlay">
                <li><button><i class="ti-search"></i></button></li>
                <li><button><i class="ti-shopping-cart"></i></button></li>
                <li><button><i class="ti-heart"></i></button></li>
              </ul>
            </div>
            <div class="card-body">
              <p>Accessories</p>
              <h4 class="card-product__title"><a href="single-product.html">Quartz Belt Watch</a></h4>
              <p class="card-product__price">$150.00</p>
            </div>
          </div>

          <div class="card text-center card-product">
            <div class="card-product__img">
              <img class="img-fluid" src="../theme/img/product/product2.png" alt="">
              <ul class="card-product__imgOverlay">
                <li><button><i class="ti-search"></i></button></li>
                <li><button><i class="ti-shopping-cart"></i></button></li>
                <li><button><i class="ti-heart"></i></button></li>
              </ul>
            </div>
            <div class="card-body">
              <p>Beauty</p>
              <h4 class="card-product__title"><a href="single-product.html">Women Freshwash</a></h4>
              <p class="card-product__price">$150.00</p>
            </div>
          </div>

          <div class="card text-center card-product">
            <div class="card-product__img">
              <img class="img-fluid" src="../theme/img/product/product3.png" alt="">
              <ul class="card-product__imgOverlay">
                <li><button><i class="ti-search"></i></button></li>
                <li><button><i class="ti-shopping-cart"></i></button></li>
                <li><button><i class="ti-heart"></i></button></li>
              </ul>
            </div>
            <div class="card-body">
              <p>Decor</p>
              <h4 class="card-product__title"><a href="single-product.html">Room Flash Light</a></h4>
              <p class="card-product__price">$150.00</p>
            </div>
          </div>

          <div class="card text-center card-product">
            <div class="card-product__img">
              <img class="img-fluid" src="../theme/img/product/product4.png" alt="">
              <ul class="card-product__imgOverlay">
                <li><button><i class="ti-search"></i></button></li>
                <li><button><i class="ti-shopping-cart"></i></button></li>
                <li><button><i class="ti-heart"></i></button></li>
              </ul>
            </div>
            <div class="card-body">
              <p>Decor</p>
              <h4 class="card-product__title"><a href="single-product.html">Room Flash Light</a></h4>
              <p class="card-product__price">$150.00</p>
            </div>
          </div>

          <div class="card text-center card-product">
            <div class="card-product__img">
              <img class="img-fluid" src="../theme/img/product/product1.png" alt="">
              <ul class="card-product__imgOverlay">
                <li><button><i class="ti-search"></i></button></li>
                <li><button><i class="ti-shopping-cart"></i></button></li>
                <li><button><i class="ti-heart"></i></button></li>
              </ul>
            </div>
            <div class="card-body">
              <p>Accessories</p>
              <h4 class="card-product__title"><a href="single-product.html">Quartz Belt Watch</a></h4>
              <p class="card-product__price">$150.00</p>
            </div>
          </div>

          <div class="card text-center card-product">
            <div class="card-product__img">
              <img class="img-fluid" src="../theme/img/product/product2.png" alt="">
              <ul class="card-product__imgOverlay">
                <li><button><i class="ti-search"></i></button></li>
                <li><button><i class="ti-shopping-cart"></i></button></li>
                <li><button><i class="ti-heart"></i></button></li>
              </ul>
            </div>
            <div class="card-body">
              <p>Beauty</p>
              <h4 class="card-product__title"><a href="single-product.html">Women Freshwash</a></h4>
              <p class="card-product__price">$150.00</p>
            </div>
          </div>

          <div class="card text-center card-product">
            <div class="card-product__img">
              <img class="img-fluid" src="../theme/img/product/product3.png" alt="">
              <ul class="card-product__imgOverlay">
                <li><button><i class="ti-search"></i></button></li>
                <li><button><i class="ti-shopping-cart"></i></button></li>
                <li><button><i class="ti-heart"></i></button></li>
              </ul>
            </div>
            <div class="card-body">
              <p>Decor</p>
              <h4 class="card-product__title"><a href="single-product.html">Room Flash Light</a></h4>
              <p class="card-product__price">$150.00</p>
            </div>
          </div>

          <div class="card text-center card-product">
            <div class="card-product__img">
              <img class="img-fluid" src="../theme/img/product/product4.png" alt="">
              <ul class="card-product__imgOverlay">
                <li><button><i class="ti-search"></i></button></li>
                <li><button><i class="ti-shopping-cart"></i></button></li>
                <li><button><i class="ti-heart"></i></button></li>
              </ul>
            </div>
            <div class="card-body">
              <p>Decor</p>
              <h4 class="card-product__title"><a href="single-product.html">Room Flash Light</a></h4>
              <p class="card-product__price">$150.00</p>
            </div>
          </div>
        </div>
      </div>
    </section>  -->
	<!-- ================ Best Selling item  carousel end ================= -->

	<!-- ================ Blog section start ================= -->
	<section class="blog">
		<div class="container">
			<div class="section-intro pb-60px">
				<p>Popular Item in the market</p>
				<h2>
					Latest <span class="section-intro__style">News</span>
				</h2>
			</div>

			<div class="row">
				<div class="col-md-6 col-lg-4 mb-4 mb-lg-0">
					<div class="card card-blog">
						<div class="card-blog__img">
							<img class="card-img rounded-0" src="../theme/img/blog/blog1.png"
								alt="">
						</div>
						<div class="card-body">
							<ul class="card-blog__info">
								<li><a href="#">By Admin</a></li>
								<li><a href="#"><i class="ti-comments-smiley"></i> 2
										Comments</a></li>
							</ul>
							<h4 class="card-blog__title">
								<a href="single-blog.html">The Richland Center Shooping News
									and weekly shooper</a>
							</h4>
							<p>Let one fifth i bring fly to divided face for bearing
								divide unto seed. Winged divided light Forth.</p>
							<a class="card-blog__link" href="#">Read More <i
								class="ti-arrow-right"></i></a>
						</div>
					</div>
				</div>

				<div class="col-md-6 col-lg-4 mb-4 mb-lg-0">
					<div class="card card-blog">
						<div class="card-blog__img">
							<img class="card-img rounded-0" src="../theme/img/blog/blog2.png"
								alt="">
						</div>
						<div class="card-body">
							<ul class="card-blog__info">
								<li><a href="#">By Admin</a></li>
								<li><a href="#"><i class="ti-comments-smiley"></i> 2
										Comments</a></li>
							</ul>
							<h4 class="card-blog__title">
								<a href="single-blog.html">The Shopping News also offers
									top-quality printing services</a>
							</h4>
							<p>Let one fifth i bring fly to divided face for bearing
								divide unto seed. Winged divided light Forth.</p>
							<a class="card-blog__link" href="#">Read More <i
								class="ti-arrow-right"></i></a>
						</div>
					</div>
				</div>

				<div class="col-md-6 col-lg-4 mb-4 mb-lg-0">
					<div class="card card-blog">
						<div class="card-blog__img">
							<img class="card-img rounded-0" src="../theme/img/blog/blog3.png"
								alt="">
						</div>
						<div class="card-body">
							<ul class="card-blog__info">
								<li><a href="#">By Admin</a></li>
								<li><a href="#"><i class="ti-comments-smiley"></i> 2
										Comments</a></li>
							</ul>
							<h4 class="card-blog__title">
								<a href="single-blog.html">Professional design staff and
									efficient equipment you’ll find we offer</a>
							</h4>
							<p>Let one fifth i bring fly to divided face for bearing
								divide unto seed. Winged divided light Forth.</p>
							<a class="card-blog__link" href="#">Read More <i
								class="ti-arrow-right"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ================ Blog section end ================= -->

	<!-- ================ Subscribe section start ================= -->
	<section class="subscribe-position">
		<div class="container">
			<div class="subscribe text-center">
				<h3 class="subscribe__title">Get Update From Anywhere</h3>
				<p>Bearing Void gathering light light his eavening unto dont
					afraid</p>
				<div id="mc_embed_signup">
					<form target="_blank"
						action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
						method="get" class="subscribe-form form-inline mt-5 pt-1">
						<div class="form-group ml-sm-auto">
							<input class="form-control mb-1" type="email" name="EMAIL"
								placeholder="Enter your email" onfocus="this.placeholder = ''"
								onblur="this.placeholder = 'Your Email Address '">
							<div class="info"></div>
						</div>
						<button class="button button-subscribe mr-auto mb-1" type="submit">Subscribe
							Now</button>
						<div style="position: absolute; left: -5000px;">
							<input name="b_36c4fd991d266f23781ded980_aefe40901a"
								tabindex="-1" value="" type="text">
						</div>

					</form>
				</div>

			</div>
		</div>
	</section>
	<!-- ================ Subscribe section end ================= -->

	</main>
</body>
</html>