<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>Learn &mdash; Free Website Template, Free HTML5 Template
	by freehtml5.co</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Free HTML5 Website Template by freehtml5.co" />
<meta name="keywords"
	content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
<meta name="author" content="freehtml5.co" />

    
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/canvasjs.min.js" />"></script>


<!-- 
	//////////////////////////////////////////////////////

	FREE HTML5 TEMPLATE 
	DESIGNED & DEVELOPED by FreeHTML5.co
		
	Website: 		http://freehtml5.co/
	Email: 			info@freehtml5.co
	Twitter: 		http://twitter.com/fh5co
	Facebook: 		https://www.facebook.com/fh5co

	//////////////////////////////////////////////////////
	 -->

<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<link
	href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,500,700,800"
	rel="stylesheet">

<!-- Animate.css -->
<link rel="stylesheet" href="resources/css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="resources/css/icomoon.css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="resources/css/bootstrap.css">

<!-- Magnific Popup -->
<link rel="stylesheet" href="resources/css/magnific-popup.css">

<!-- Owl Carousel  -->
<link rel="stylesheet" href="resources/css/owl.carousel.min.css">
<link rel="stylesheet" href="resources/css/owl.theme.default.min.css">

<!-- Theme style  -->
<link rel="stylesheet" href="resources/css/style.css">

<!-- Modernizr JS -->
<script src="resources/js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="resources/js/respond.min.js"></script>
	<![endif]-->

</head>
<body>

	<div class="fh5co-loader"></div>

	<div id="page">
		<nav class="fh5co-nav" role="navigation">
		<div class="top">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 text-right">
						
						<ul class="fh5co-social">
							<li><a href="https://twitter.com/championsleague" target="_blank"><i class="icon-twitter"></i></a></li>
							<li><a href="https://www.facebook.com/ChampionsLeague/" target="_blank"><i class="icon-facebook"></i></a></li>
							<li><a href="https://www.instagram.com/championsleague/?hl=en" target="_blank"><i class="icon-instagram"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="top-menu">
			<div class="container">
				<div class="row">
					<div class="col-xs-1">
						<div id="fh5co-logo"><a href="./">ChampionsLeague<span>.</span></a></div>
					</div>
					<div class="col-xs-11 text-right menu-1">
						<ul>
							<li class="active"><a href="./">Home</a></li>
							<li><a href="historicalData?decadeId=1">CL Finalists' History</a></li>
							<li><a href="topTeams">All-time TOP teams</a></li>
							
							<li><a href="worldwideGeo">Worldwide Popularity</a></li>
							
						</ul>
					</div>
				</div>
				
			</div>
		</div>
	</nav>

		<header id="fh5co-header" class="fh5co-cover fh5co-cover-sm"
			role="banner"
			style="background-image: url(resources/images/img_bg_2.jpg);"
			data-stellar-background-ratio="0.5">
			<div class="overlay"></div>
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-md-offset-2 text-center">
					 	<div class="display-t" style="height:500px">
							<div class="display-tc animate-box" data-animate-effect="fadeIn" style="height:500px">					
								<h1>Match Schedule</h1>
								<h2>Champions League 2018
								</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
		</header>


		<div id="fh5co-project" style="padding-top:0px">
			<div class="container-fluid proj-bottom" style="padding-bottom:0%">
			
		<div class="content mt-3">
          <div class="col-xl-12">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                        
                            
<div class="w3-bar w3-black">
  <button class="w3-bar-item w3-button" onclick="openCity('quarter')">Quarter-Final</button>
  <button class="w3-bar-item w3-button" onclick="openCity('semi')">Semi-Final</button>
  <button class="w3-bar-item w3-button" onclick="openCity('final')">Final</button>
</div>




<script>
function openCity(cityName) {

    var i;
    var x = document.getElementsByClassName("city");
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";  
    }
   
    document.getElementById(cityName).style.display = "block";  
}


</script>

			<div id="quarter" class="w3-container w3-display-container city" style="padding-top:20px">
                <c:forEach var="fixture" items="${quarters}">
                
                  <div class="gallery">
                  <div class="col-md-4 col-sm-6 fh5co-project animate-box"
							data-animate-effect="fadeIn">
                    <a href=
                      "fetch?matchId=${fixture.matchId}&homeTeamId=${fixture.homeTeamId}&awayTeamId=${fixture.awayTeamId}&homeTeam=${fixture.homeTeamName}&awayTeam=${fixture.awayTeamName}&stadium=${fixture.stadium}">
                      <img src="<c:url value="/resources/data/images_teams/${fixture.matchId}.jpg"/>"
                      height="265" width="500" />
                      </a>
                    <div class="desc" align="center">${fixture.homeTeamName} vs ${fixture.awayTeamName}</div>
                    <div class="desc2" align="center">${fixture.matchTime}</div>
                    <div class="desc2" align="center">${fixture.stadium}</div>
                  </div></div>
                </c:forEach>
              </div>
              
              <div id="semi" class="w3-container w3-display-container city"
                style="display: none;padding-top:20px">
                <c:forEach var="fixture" items="${semis}">
                
                  <div class="gallery">
                  <div class="col-md-4 col-sm-6 fh5co-project animate-box"
							data-animate-effect="fadeIn">
                    <a href=
                      "fetch?matchId=${fixture.matchId}&homeTeamId=${fixture.homeTeamId}&awayTeamId=${fixture.awayTeamId}&homeTeam=${fixture.homeTeamName}&awayTeam=${fixture.awayTeamName}
                      &stadium=${fixture.stadium}">
                      <img src="<c:url value="/resources/data/images_teams/${fixture.matchId}.jpg"/>"
                      height="265" width="500" />
                      </a>
                    <div class="desc" align="center">${fixture.homeTeamName} vs ${fixture.awayTeamName}</div>
                    <div class="desc2" align="center">${fixture.matchTime}</div>
                    <div class="desc2" align="center">${fixture.stadium}</div>
                  </div></div>
                </c:forEach>
              </div>

              <div id="final" class="w3-container w3-display-container city"
                style="display: none;padding-top:20px">
                <c:forEach var="fixture" items="${finals}">
                
                  <div class="gallery">
                  <div class="col-md-4 col-sm-6 fh5co-project animate-box"
							data-animate-effect="fadeIn">
                    <a href=
                      "fetch?matchId=${fixture.matchId}&homeTeamId=${fixture.homeTeamId}&awayTeamId=${fixture.awayTeamId}&homeTeam=${fixture.homeTeamName}&awayTeam=${fixture.awayTeamName}&stadium=${fixture.stadium}">
                      <img src="<c:url value="/resources/data/images_teams/${fixture.matchId}.jpg"/>"
                      height="265" width="500" />
                      </a>
                   <div class="desc" align="center">${fixture.homeTeamName} vs ${fixture.awayTeamName}</div>
                    <div class="desc2" align="center">${fixture.matchTime}</div>
                    <div class="desc2" align="center">${fixture.stadium}</div>                  </div></div>
                </c:forEach>
              </div>

</div></div></div></div>
</div>
</div></div>



		
		<footer id="fh5co-footer" role="contentinfo" style="padding-top:30px;padding-bottom:20px">
			<div class="container">
				
				<div class="row copyright">
				<div class="col-md-12 text-center">
					<p>
						<small class="block"> <a href="http://www.mcrlab.net/" target="_blank"> MCR LAB </a></small> 
						<small class="block">Designed under guidance of <a href="http://www.site.uottawa.ca/~elsaddik/" target="_blank">Prof. Abdulmotaleb El Saddik </a> </small>
					</p>
					
				</div>
			</div>

			</div>
		</footer>
	</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>

	<!-- jQuery -->
	<script src="resources/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="resources/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="resources/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="resources/js/jquery.waypoints.min.js"></script>
	<!-- Stellar Parallax -->
	<script src="resources/js/jquery.stellar.min.js"></script>
	<!-- Carousel -->
	<script src="resources/js/owl.carousel.min.js"></script>
	<!-- countTo -->
	<script src="resources/js/jquery.countTo.js"></script>
	<!-- Magnific Popup -->
	<script src="resources/js/jquery.magnific-popup.min.js"></script>
	<script src="resources/js/magnific-popup-options.js"></script>
	<!-- Main -->
	<script src="resources/js/main.js"></script>





</body>
</html>

