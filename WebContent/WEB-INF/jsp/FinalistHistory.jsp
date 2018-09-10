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


<script type="text/javascript" src="https://d3js.org/d3.v3.js"></script>
<link rel="stylesheet" href="resources/css/legends.css">


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


<style type="text/css">
.dropbtn {
	background-color: #4CAF50;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
	cursor: pointer;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
	position: relative;
	display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {
	background-color: #f1f1f1
}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {
	display: block;
}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {
	background-color: #3e8e41;
}
</style>

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
								<li><a href="https://twitter.com/championsleague"
									target="_blank"><i class="icon-twitter"></i></a></li>
								<li><a href="https://www.facebook.com/ChampionsLeague/"
									target="_blank"><i class="icon-facebook"></i></a></li>
								<li><a
									href="https://www.instagram.com/championsleague/?hl=en"
									target="_blank"><i class="icon-instagram"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="top-menu">
				<div class="container">
					<div class="row">
						<div class="col-xs-1">
							<div id="fh5co-logo">
								<a href="./">ChampionsLeague<span>.</span></a>
							</div>
						</div>
						<div class="col-xs-11 text-right menu-1">
							<ul>
								<li><a href="./">Home</a></li>
								<li class="active"><a href="historicalData?decadeId=1">CL
										Finalists' History</a></li>
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
						<div class="display-t">
							<div class="display-tc animate-box" data-animate-effect="fadeIn">
								<h1>Finalists' History</h1>
								<h2>Teams which made to the finals in last 20 seasons</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
		</header>


		<div id="fh5co-explore" class="fh5co-bg-section"
			style="background: white">
			<div class="container">
				<div class="setmargin">
					<div class="col-xl-12">
						<div class="card" style="height: 770px; margin-top: 20px;">
							<center>
								<h3>Year wise summary</h3>
							</center>
							<br>

							<!-- Code for 5-year period dropdown -->
							<center>
								<div class="dropdown">
									<button class="dropbtn">Select 5-year period</button>
									<div class="dropdown-content">
										<a href="historicalData?decadeId=1">2011-2015</a> <a
											href="historicalData?decadeId=2">2006-2010</a> <a
											href="historicalData?decadeId=3">2001-2005</a> <a
											href="historicalData?decadeId=4">1996-2000</a> <a
											href="historicalData?decadeId=5">1991-1995</a>
									</div>
								</div>

							</center>

							<div id="legend2">
								<h3></h3>

								<div class="legend2">
									<p class="country-name">
										<span class="key-dot bronx"></span>Winner
									</p>
								</div>
								<div class="legend2">
									<p class="country-name">
										<span class="key-dot kings"></span>Runners up
									</p>
								</div>
							</div>

							<br /> <br />

							<svg width="960" height="600"
								style="padding-top: 24px; padding-right: 10px;"></svg>
							<script src="https://d3js.org/d3.v4.min.js"></script>
							<script>
							<!-- Chart implemenation -->
								// main svg
								var svg = d3.select("svg"), width = +svg
										.attr("width"), height = +svg
										.attr("height"), g = svg.append("g")
										.attr("transform", "translate(20,0)"); // move right 20px.

								// x-scale and x-axis
								var experienceName = [ "", "1", "2", "3", "4",
										"5" ];
								var formatSkillPoints = function(d) {
									return experienceName[d % 6];
								}
								var xScale = d3.scaleLinear().domain([ 0, 5 ])
										.range([ 0, 400 ]);

								var xAxis = d3.axisTop().scale(xScale).ticks(5)
										.tickFormat(formatSkillPoints);

								// Setting up a way to handle the data
								var tree = d3
										.cluster()
										// This D3 API method setup the Dendrogram datum position.
										.size([ height, width - 460 ])
										// Total width - bar chart width = Dendrogram chart width
										.separation(
												function separate(a, b) {
													return a.parent == b.parent // 2 levels tree grouping for category
															|| a.parent.parent == b.parent
															|| a.parent == b.parent.parent ? 0.4
															: 0.8;
												});

								var stratify = d3.stratify() // This D3 API method gives cvs file flat data array dimensions.
								.parentId(
										function(d) {
											return d.id.substring(0, d.id
													.lastIndexOf("."));
										});

								d3
										.csv(
												"<c:url value='/resources/data/TreeData_${decadeId}.csv'/>",
												row,
												function(error, data) {
													if (error)
														throw error;

													var root = stratify(data);
													tree(root);

													// Draw every datum a line connecting to its parent.
													var link = g
															.selectAll(".link")
															.data(
																	root
																			.descendants()
																			.slice(
																					1))
															.enter()
															.append("path")
															.attr("class",
																	"link")
															.attr(
																	"d",
																	function(d) {
																		return "M"
																				+ d.y
																				+ ","
																				+ d.x
																				+ "C"
																				+ (d.parent.y + 100)
																				+ ","
																				+ d.x
																				+ " "
																				+ (d.parent.y + 100)
																				+ ","
																				+ d.parent.x
																				+ " "
																				+ d.parent.y
																				+ ","
																				+ d.parent.x;
																	});

													// Setup position for every datum; Applying different css classes to parents and leafs.
													var node = g
															.selectAll(".node")
															.data(
																	root
																			.descendants())
															.enter()
															.append("g")
															.attr(
																	"class",
																	function(d) {
																		return "node"
																				+ (d.children ? " node--internal"
																						: " node--leaf");
																	})
															.attr(
																	"transform",
																	function(d) {
																		return "translate("
																				+ d.y
																				+ ","
																				+ d.x
																				+ ")";
																	});

													// Draw every datum a small circle.
													node.append("circle").attr(
															"r", 4);

													// Setup G for every leaf datum.
													var leafNodeG = g
															.selectAll(
																	".node--leaf")
															.append("g")
															.attr("class",
																	"node--leaf-g")
															.attr(
																	"transform",
																	"translate("
																			+ 8
																			+ ","
																			+ -13
																			+ ")");

													leafNodeG
															.append("rect")
															.attr("class",
																	"shadow")
															.style(
																	"fill",
																	function(d) {
																		return d.data.color;
																	})
															.attr("width", 2)
															.attr("height", 30)
															.attr("rx", 2)
															.attr("ry", 2)
															.transition()
															.duration(800)
															.attr(
																	"width",
																	function(d) {
																		return xScale(d.data.value);
																	});

													leafNodeG
															.append("image")
															.attr(
																	"xlink:href",
																	function(d) {
																		return "resources/data/logo/"
																				+ d.data.imagename
																				+ ".jpg";
																	}).attr(
																	"x", 0)
															.attr("y", 0)
															.attr("width", 25)
															.attr("height", 30);

													leafNodeG
															.append("text")
															.text(
																	function(d) {
																		return d.data.id
																				.substring(d.data.id
																						.lastIndexOf(".") + 1)
																	}).attr(
																	"x", 30)
															.attr("y", 22);

													// Write down text for every parent datum
													var internalNode = g
															.selectAll(".node--internal");
													internalNode
															.append("text")
															.attr("y", -10)
															.style(
																	"text-anchor",
																	"middle")
															.text(
																	function(d) {
																		return d.data.id
																				.substring(d.data.id
																						.lastIndexOf(".") + 1);
																	});

													// Attach axis on top of the first leaf datum.
													var firstEndNode = g
															.select(".node--leaf");
													firstEndNode
															.insert("g")
															.attr("class",
																	"xAxis")
															.attr(
																	"transform",
																	"translate("
																			+ 7
																			+ ","
																			+ -14
																			+ ")")
															.call(xAxis);

													// tick mark for x-axis
													firstEndNode
															.insert("g")
															.attr("class",
																	"grid")
															.attr(
																	"transform",
																	"translate(7,"
																			+ (height - 15)
																			+ ")")
															.call(
																	d3
																			.axisBottom()
																			.scale(
																					xScale)
																			.ticks(
																					5)
																			.tickSize(
																					-height,
																					0,
																					0)
																			.tickFormat(
																					""));
													svg.select(".xAxis")
															.selectAll("text")
															.remove();

													svg
															.selectAll(
																	".xAxis .tick")
															.each(
																	function(d) {
																		console
																				.log(
																						"d",
																						d);
																		var p = d3
																				.select(this);
																		p
																				.append(
																						"svg:image")
																				.attr(
																						"x",
																						-30)
																				.attr(
																						"y",
																						-50)
																				.attr(
																						"dy",
																						".35em")
																				.attr(
																						"width",
																						45)
																				.attr(
																						"height",
																						50)
																				.attr(
																						"xlink:href",
																						"resources/data/logo/"
																								+ d
																								+ ".jpg");
																	});
													// Emphasize the y-axis baseline.
													svg
															.selectAll(".grid")
															.select("line")
															.style(
																	"stroke-dasharray",
																	"20,1")
															.style("stroke",
																	"black");

													// The moving ball
													var ballG = svg
															.insert("g")
															.attr("class",
																	"ballG")
															.attr(
																	"transform",
																	"translate("
																			+ 1100
																			+ ","
																			+ height
																			/ 2
																			+ ")");
													ballG
															.insert("circle")
															.attr("class",
																	"shadow")
															.style("fill",
																	"steelblue")
															.attr("r", 5);
													ballG.insert("text").style(
															"text-anchor",
															"middle").attr(
															"dy", 5)
															.text("0.0");
													// Animation functions for mouse on and off events.
													d3
															.selectAll(
																	".node--leaf-g")
															.on("mouseover",
																	handleMouseOver)
															.on("mouseout",
																	handleMouseOut);
													function handleMouseOver(d) {
														var leafG = d3
																.select(this);

														leafG
																.select("rect")
																.attr("stroke",
																		"#4D4D4D")
																.attr(
																		"stroke-width",
																		"2");

														var ballGMovement = ballG
																.transition()
																.duration(400)
																.attr(
																		"transform",
																		"translate("
																				+ (d.y
																						+ xScale(d.data.value) + 90)
																				+ ","
																				+ (d.x + 1.5)
																				+ ")");

														ballGMovement
																.select(
																		"circle")
																.style(
																		"fill",
																		d.data.color)
																.attr("r", 18);

														ballGMovement
																.select("text")
																.delay(300)
																.text(
																		Number(
																				d.data.value)
																				.toFixed(
																						1));
													}
													function handleMouseOut() {
														var leafG = d3
																.select(this);

														leafG
																.select("rect")
																.attr(
																		"stroke-width",
																		"0");
													}
												});
								function row(d) {
									return {
										id : d.id,
										value : +d.value,
										color : d.color,
										imagename : d.imagename
									};
								}
							</script>

						</div>
					</div>
				</div>

			</div>

		</div>




		<footer id="fh5co-footer" role="contentinfo"
			style="padding-top: 30px; padding-bottom: 20px">
			<div class="container">


				<div class="row copyright">
					<div class="col-md-12 text-center">
						<p>
							<small class="block"> <a href="http://www.mcrlab.net/"
								target="_blank"> MCR LAB </a></small> <small class="block">Designed
								under guidance of <a
								href="http://www.site.uottawa.ca/~elsaddik/" target="_blank">Prof.
									Abdulmotaleb El Saddik </a>
							</small>
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

	<!-- <script type="text/javascript" src="resources/js/legends.js"></script> -->



</body>
</html>

