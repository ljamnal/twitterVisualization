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

   
 <script src="https://cdn.anychart.com/releases/8.2.0/js/anychart-base.min.js"></script>
  <script src="https://cdn.anychart.com/releases/8.2.0/js/anychart-ui.min.js"></script>
  <script src="https://cdn.anychart.com/releases/8.2.0/js/anychart-exports.min.js"></script>
  <script src="https://cdn.anychart.com/releases/8.2.0/js/anychart-map.min.js"></script>
  <script src="https://cdn.anychart.com/releases/8.2.0/js/anychart-data-adapter.min.js"></script>
  <script src="https://cdn.anychart.com/geodata/1.2.0/custom/world/world.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/proj4js/2.3.15/proj4.js"></script>
  <link rel="stylesheet" href="https://cdn.anychart.com/releases/8.2.0/css/anychart-ui.min.css" />
  <link rel="stylesheet" href="https://cdn.anychart.com/releases/8.2.0/fonts/css/anychart-font.min.css" />
  

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
							<li><a href="./">Home</a></li>
							<li><a href="historicalData?decadeId=1">CL Finalists' History</a></li>
							<li><a href="topTeams">All-time TOP teams</a></li>
							
							<li class="active"><a href="worldwideGeo">Worldwide Popularity</a></li>
							
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
								<h1>Worldwide Popularity</h1>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</header>



		<div id="fh5co-explore" class="fh5co-bg-section" style="background: white">
			<div class="container">

		   <div id="container" style="height:600px"></div>
    <script type="text/javascript">
anychart.onDocumentReady(function() {
  // The data used in this sample can be obtained from the CDN
  // https://cdn.anychart.com/samples/maps-general-features/world-choropleth-map/data.json
  anychart.data.loadJsonFile('resources/data/data.json', function(data) {
    var map = anychart.map();

    map.credits()
      .enabled(true)
      .url('https://en.wikipedia.org/wiki/List_of_sovereign_states_and_dependent_territories_by_population_density')
      .logoSrc('https://en.wikipedia.org/static/favicon/wikipedia.ico')
      .text('Data source: https://en.wikipedia.org/wiki/List_of_sovereign_states_and_dependent_territories_by_population_density');

    map.title()
      .enabled(true)
      .useHtml(true)
      .padding([10, 0, 10, 0])
      .text('No. of tweets<br/>' +
        '<span  style="color:#929292; font-size: 12px;">(Country wise)</span>');

    map.geoData('anychart.maps.world');
    map.interactivity().selectionMode('none');
    map.padding(0);

    var dataSet = anychart.data.set(data);
    var density_data = dataSet.mapAs({
      'value': 'color'
    });
    var series = map.choropleth(density_data);

    series.labels(false);

    series.hovered()
      .fill('#663300')
      .stroke(anychart.color.darken('#f48fb1'));

    series.selected()
      .fill('#c2185b')
      .stroke(anychart.color.darken('#c2185b'));

    series.tooltip()
      .useHtml(true)
      .format(function() {
        return '<span style="color: #d9d9d9">Negative</span>: ' +
          parseFloat(this.getData('density')).toLocaleString() + ' tweets <br/>' +
          '<span style="color: #d9d9d9">Neutral</span>: ' +
          parseInt(this.getData('population')).toLocaleString() + 'tweets <br/>' +
          '<span style="color: #d9d9d9">Positive</span>: ' +
          parseInt(this.getData('area')).toLocaleString() + ' tweets';
      });

    var scale = anychart.scales.ordinalColor([{
        less: 10
      },
      {
        from: 10,
        to: 20
      },
      {
        greater: 20
      }
    ]);
   /*   scale.colors(['#81d4fa', '#4fc3f7', '#29b6f6', '#039be5', '#0288d1', '#0277bd', '#01579b', '#014377', '#000000']);  */
	scale.colors(['#4bbc21', '#e8173d', '#fce38d']); 
   
    var colorRange = map.colorRange();
    colorRange.enabled(true)
      .padding([0, 0, 20, 0]);
    colorRange.ticks()
      .enabled(true)
      .stroke('3 #ffffff')
      .position('center')
      .length(7);
    colorRange.colorLineSize(5);
    colorRange.marker().size(7);
    colorRange.labels()
      .fontSize(11)
      .padding(3, 0, 0, 0)
      .format(function() {
        var range = this.colorRange;
        console.log(range);
        var name;
        if (range.start == 10) {
          name = 'Negative';
        } else if (range.start == 20) {
          name = 'Neutral';
        } else {
          name = 'Positive';
        }
        return name
      });

    series.colorScale(scale);

    // create zoom controls
    var zoomController = anychart.ui.zoom();
    zoomController.render(map);

    // set container id for the chart
    map.container('container');
    // initiate chart drawing
    map.draw();
  });
});
    </script>

			</div>
		</div>

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

