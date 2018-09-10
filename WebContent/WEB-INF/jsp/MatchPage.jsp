<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Learn &mdash; Free Website Template, Free HTML5 Template by freehtml5.co</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Website Template by freehtml5.co" />
	<meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
	<meta name="author" content="freehtml5.co" />

<script type="text/javascript" src="https://d3js.org/d3.v3.js"></script>
<link rel="stylesheet" href="resources/css/legends.css">

  <script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	
	<script type="text/javascript"
	src="<c:url value="/resources/js/canvasjs.min.js" />"></script>
    
 <script src="https://cdn.anychart.com/releases/8.2.0/js/anychart-base.min.js"></script>
  <script src="https://cdn.anychart.com/releases/8.2.0/js/anychart-ui.min.js"></script>
  <script src="https://cdn.anychart.com/releases/8.2.0/js/anychart-exports.min.js"></script>
  <script src="https://cdn.anychart.com/releases/8.2.0/js/anychart-map.min.js"></script>
  <script src="https://cdn.anychart.com/releases/8.2.0/js/anychart-data-adapter.min.js"></script>
  <script src="https://cdn.anychart.com/geodata/1.2.0/custom/world/world.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/proj4js/2.3.15/proj4.js"></script>
  <link rel="stylesheet" href="https://cdn.anychart.com/releases/8.2.0/css/anychart-ui.min.css" />
  <link rel="stylesheet" href="https://cdn.anychart.com/releases/8.2.0/fonts/css/anychart-font.min.css" />
  
  
  
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
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<link href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,500,700,800" rel="stylesheet">
	
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

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">


<style>

.d3-tip {
  line-height: 1;
  font-weight: bold;
  padding: 12px;
  background: rgba(0, 0, 0, 0.8);
  color: #fff;
  border-radius: 2px;
}

/* Creates a small triangle extender for the tooltip */
.d3-tip:after {
  box-sizing: border-box;
  display: inline;
  font-size: 10px;
  width: 100%;
  line-height: 1;
  color: rgba(0, 0, 0, 0.8);
  content: "\25BC";
  position: absolute;
  text-align: center;
}

/* Style northward tooltips differently */
.d3-tip.n:after {
  margin: -1px 0 0 0;
  top: 100%;
  left: 0;
}
</style>

	</head>
	<body>
		
		
		
		
		<script type="text/javascript">
		$(function() {
			var dps = []//= ${dps1}; //dataPoints.
			var dps2 = []//= ${dps2}; //dataPoints.
			
			var max
			var obj1 = JSON.parse('${json1}');
			var i;
			for (i = 0; i < obj1.length; i++) {
				//text += cars[i] + "<br>";
				var a1 = obj1[i].x;
				var b1 = obj1[i].y;
				dps.push({
					x : a1,
					y : b1
				});
			}

			var obj2 = JSON.parse('${json2}');
			var j;
			for (j = 0; j < obj2.length; j++) {
				//text += cars[i] + "<br>";
				var a2 = obj2[j].x;
				var b2 = obj2[j].y;
				max = a2;
				dps2.push({
					x : a2,
					y : b2
				});
			}

			var chart = new CanvasJS.Chart("chartContainer", {
				
				interactivityEnabled: true,  //try changing it to true
				axisX: {
					title: "Time in minutes (chart updates every 30 secs)"
				},
				axisY:{
					title: "Sentiment",
					gridThickness : 0,
					maximum : 1,
					interval : 1,
					minimum : -1
				},
				legend: {
					cursor:"pointer",
					verticalAlign: "top",
					fontSize: 22,
					fontColor: "dimGrey",
					itemclick: function (e) {
		                //console.log("legend click: " + e.dataPointIndex);
		                //console.log(e);
		                if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
		                    e.dataSeries.visible = false;
		                } else {
		                    e.dataSeries.visible = true;
		                }
		 
		                e.chart.render();
		            }
				},
				data : [ {
					type : "line",
					name : '${homeTeam}',
					showInLegend: true,
					dataPoints : dps,
				
				}, {
					type : "line",
					name : '${awayTeam}',
					showInLegend: true,
					dataPoints : dps2,
					lineColor: "#ff8c00",
					legendMarkerColor: "#ff8c00",
					markerColor: "#ff8c00",
					
				}]
			});

			chart.render();
			var xVal = a2 + 0.5;
			var yVal = 15;
			var yVal2 = 15;
			var updateInterval = 30000;

			var updateChart = function() {

				$.ajax({
					url : 'liveData',
					contentType : 'application/json',
					data : {
						time : xVal,
						matchId : ${matchId},
						homeTeamId : ${homeTeamId},
						awayTeamId : ${awayTeamId},
						normalize : ${normalize}
					},
					success : function(data) {

						//var obj = JSON.parse(data);
						//$('#result').html(data);
						//document.write(data.x);
						//document.write(data.y);
						yVal = data.x;
						yVal2 = data.y;

						dps.push({
							x : xVal,
							y : yVal
						});
						dps2.push({
							x : xVal,
							y : yVal2
						});
						xVal = xVal + 0.5;

						chart.render();
					},
					error : function(xhr, status, error) {
						//var err = eval("(" + xhr.responseText + ")");
						//alert(xhr.responseText);
						//alert(error + "\r\n" + xhr.statusText + "\r\n"
						//	+ xhr.responseText);
					}
				});

			};
			setInterval(function() {
				updateChart()
			}, updateInterval);
		});
		
		$(function() {
			var dps3 = []
			var dps4 = []
			
			var max

			var obj3 = JSON.parse('${json3}');
			var k;
			for (k = 0; k < obj3.length; k++) {
				//text += cars[i] + "<br>";
				var a3 = obj3[k].x;
				var b3 = obj3[k].y;
				dps3.push({
					x : a3,
					y : b3
				});
			}
			
			var obj4 = JSON.parse('${json4}');
			var l;
			for (l = 0; l < obj4.length; l++) {
				//text += cars[i] + "<br>";
				var a4 = obj4[l].x;
				var b4 = obj4[l].y;
				dps4.push({
					x : a4,
					y : b4
				});
			}

			var chart = new CanvasJS.Chart("chartContainerSentiment", {
				
				interactivityEnabled: true,  //try changing it to true
				axisX: {
					title: "Time (in minutes)"
				},
				axisY:{
					title: "Sentiment",
					gridThickness : 0,
					maximum : 1,
					interval : 1,
					minimum : -1
				},
				legend: {
					cursor:"pointer",
					verticalAlign: "top",
					fontSize: 22,
					fontColor: "dimGrey",
					itemclick: function (e) {
		                //console.log("legend click: " + e.dataPointIndex);
		                //console.log(e);
		                if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
		                    e.dataSeries.visible = false;
		                } else {
		                    e.dataSeries.visible = true;
		                }
		 
		                e.chart.render();
		            }
				},
				data : [ 
				{
					type:"line",
					showInLegend: true,
					name : "Avg. "+ '${homeTeam}',
					dataPoints : dps3,
				
				},
				{
					type:"line",
					name : "Avg. " + '${awayTeam}',
					showInLegend: true,
					dataPoints : dps4,
				
				}]
			});

			chart.render();

		});
	</script>

		
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
	
	<header id="fh5co-header" class="fh5co-cover fh5co-cover-sm" role="banner" style="background-image:url(resources/images/img_bg_2.jpg);" data-stellar-background-ratio="0.5">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center">
					<div class="display-t" style="height:500px">
						<div class="display-tc animate-box" data-animate-effect="fadeIn" style="height:500px">
							<h1>${homeTeam} vs ${awayTeam}</h1>
							<h2>${stadium}</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	
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


<div class="w3-bar w3-black">
  <button class="w3-bar-item w3-button" onclick="openCity('liveGraph')">Live Analysis</button>
  <button class="w3-bar-item w3-button" onclick="openCity('sentiment')">Sentiment Summarization</button>
  <button class="w3-bar-item w3-button" onclick="openCity('geography')">Geographical Analysis</button>
  <button class="w3-bar-item w3-button" onclick="openCity('comparison')">Tweets Comparison</button>
</div>
<br><br>


<div id="liveGraph" class="w3-container w3-display-container city" style="padding-top:20px">
<center><h3><b>Live Sentiment Analysis</b></h3>
		<h6>Visualization of the real-time live tweets</h6></center>
	<div id="fh5co-explore" class="fh5co-bg-section" style="background: white;padding-top:50px; padding-bottom:50px;">
		<div class="container">
		
			<div id="chartContainer" style="height:400px"></div>
			</div></div>
			</div>
			
<div id="geography" class="w3-container w3-display-container city" style="display: none;padding-top:20px">
<center><h3><b>Geographical Analysis</b></h3>
		<h6>Representation of tweet count and sentiment for this match from different parts of the world</h6></center>


<div id="fh5co-explore" class="fh5co-bg-section" style="background: white;padding-top:50px; padding-bottom:50px;">
		<div class="container" style="height:550px">
			  <div class="col-xl-12">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-4">
                               <!--   <h4 class="card-title mb-0">Worldwide Tweets</h4>
                                <div class="small text-muted">Based on sentiments</div>-->
                            </div>
                            <!--/.col-->
                        </div><!--/.row-->
                        
                 <div id="container"></div>
                 
    <script type="text/javascript">
anychart.onDocumentReady(function() {
  // The data used in this sample can be obtained from the CDN
  // https://cdn.anychart.com/samples/maps-general-features/world-choropleth-map/data.json
  anychart.data.loadJsonFile('resources/data/UpdatedGeodata_${matchId}.json', function(data) {
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
        return '<span style="color: #d9d9d9"><b>${homeTeam}</b></span>: ' +
          parseInt(this.getData('homeTotalCount')).toLocaleString() + ' tweets <br/>' +
          '<span style="color: #d9d9d9">Positive</span>: ' +
          parseInt(this.getData('homePositiveCount')).toLocaleString() +
          '<span style="color: #d9d9d9">, Negative</span>: ' +
          parseInt(this.getData('homeNegativeCount')).toLocaleString()+
          '<span style="color: #d9d9d9">, Neutral</span>: ' +
          parseInt(this.getData('homeNeutralCount')).toLocaleString()+ ' <br><br>'+
          '<span style="color: #d9d9d9"><b>${awayTeam}</b></span>: ' +
          parseInt(this.getData('awayTotalCount')).toLocaleString() + ' tweets <br/>'+
          '<span style="color: #d9d9d9">Positive</span>: ' +
          parseInt(this.getData('awayPositiveCount')).toLocaleString()+
          '<span style="color: #d9d9d9">, Negative</span>: ' +
          parseInt(this.getData('awayNegativeCount')).toLocaleString()+
          '<span style="color: #d9d9d9">, Neutral</span>: ' +
          parseInt(this.getData('awayNeutralCount')).toLocaleString();
      });

    var scale = anychart.scales.ordinalColor([{
        less: 0.5
      },
      {
        greater: 0.5
      }
    ]);
    scale.colors(['#3366ff', '#ff8c00']);

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
        var name;
        if (isFinite(range.start + range.end)) {
          name = range.start + ' - ' + range.end;
        } else if (isFinite(range.start)) {
        	 name = '${awayTeam} ';
        } else {
          name = '${homeTeam} ';
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

        </div> <!-- .content -->

			</div></div>
			</div>
			
			
			<div id="sentiment" class="w3-container w3-display-container city" style="display: none;padding-top:20px">
<center><h3><b>Sentiment Summarization</b></h3>
		<h6>Summarization of tweets</h6></center>
	<div id="fh5co-explore" class="fh5co-bg-section" style="background: white;padding-top:50px; padding-bottom:50px;">
		<div class="container">
		
			<div id="chartContainerSentiment" style="height:400px;width:900px"></div>
			</div></div>
			</div>

<div id="comparison" class="w3-container w3-display-container city" style="display: none;padding-top:20px">	
		<center><h3><b>Tweets Comparison</b></h3>
		<h6>Team wise sentiment analysis</h6></center>
			
			<div id="fh5co-explore" class="fh5co-bg-section" style="background: white;padding-top:40px; padding-bottom:40px;">
		<div class="container">
		<center>
			<div class="col-xl-12">
                <div class="card">
                    <div class="card-body">
			
			<svg width="960" height="200"></svg>
<script src="https://d3js.org/d3.v4.min.js"></script>
<script>



var svg = d3.select("svg"),
    margin = {top: 20, right: 20, bottom: 30, left: 48},
    width = +svg.attr("width") - margin.left - margin.right,
    height = +svg.attr("height") - margin.top - margin.bottom,
    g = svg.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");
    

var y = d3.scaleBand()			// x = d3.scaleBand()	
    .rangeRound([0, height])	// .rangeRound([0, width])
    .paddingInner(0.05)
    .align(0.1);

var x = d3.scaleLinear()		// y = d3.scaleLinear()
    .rangeRound([0, width]);	// .rangeRound([height, 0]);

var z = d3.scaleOrdinal()
    .range(["#ff8c00", "#3366ff"]);


d3.csv("resources/data/TweetsSummaryMatch.csv", function(d, i, columns) {
  for (i = 1, t = 0; i < columns.length; ++i) t += d[columns[i]] = +d[columns[i]];
  d.total = t;
  return d;
}, function(error, data) {
  if (error) throw error;

  var keys = data.columns.slice(1);

  data.sort(function(a, b) { return b.total - a.total; });
  y.domain(data.map(function(d) { return d.State; }));					// x.domain...
  x.domain([0, d3.max(data, function(d) { return d.total; })]).nice();	// y.domain...
  z.domain(keys);

  g.append("g")
    .selectAll("g")
    .data(d3.stack().keys(keys)(data))
    .enter().append("g")
      .attr("fill", function(d) { return z(d.key); })
    .selectAll("rect")
    .data(function(d) { return d; })
    .enter().append("rect")
      .attr("y", function(d) { return y(d.data.State); })	    //.attr("x", function(d) { return x(d.data.State); })
      .attr("x", function(d) { return x(d[0]); })			    //.attr("y", function(d) { return y(d[1]); })	
      .attr("width", function(d) { return x(d[1]) - x(d[0]); })	//.attr("height", function(d) { return y(d[0]) - y(d[1]); })
      .attr("height", y.bandwidth())
.append("svg:title")
   .text(function(d) { return d[1]-d[0] + " Tweets"; });						    //.attr("width", x.bandwidth());	

  g.append("g")
      .attr("class", "axis")
      .attr("transform", "translate(0,0)") 						//  .attr("transform", "translate(0," + height + ")")
      .call(d3.axisLeft(y));									//   .call(d3.axisBottom(x));

  g.append("g")
      .attr("class", "axis")
	  .attr("transform", "translate(0,"+height+")")				// New line
      .call(d3.axisBottom(x).ticks(null, "s"))					//  .call(d3.axisLeft(y).ticks(null, "s"))
    .append("text")
      .attr("y", 35)												//     .attr("y", 2)
      .attr("x", x(x.ticks().pop()) + 0.5) 						//     .attr("y", y(y.ticks().pop()) + 0.5)
      .attr("dy", "0.32em")										//     .attr("dy", "0.32em")
      .attr("fill", "#000")
      .attr("font-weight", "bold")
      .attr("text-anchor", "start")
      .text("Tweets")
	  .attr("transform", "translate("+ (-width) +",-10)");   	// Newline

  var legend = g.append("g")
      .attr("font-family", "sans-serif")
      .attr("font-size", 10)
      .attr("text-anchor", "end")
    .selectAll("g")
    .data(keys.slice().reverse())
    .enter().append("g")
    //.attr("transform", function(d, i) { return "translate(0," + i * 20 + ")"; });
	 .attr("transform", function(d, i) { return "translate(-50," + (300 + i * 20) + ")"; });

  legend.append("rect")
      .attr("x", width - 19)
      .attr("width", 19)
      .attr("height", 19)
      .attr("fill", z);

  legend.append("text")
      .attr("x", width - 24)
      .attr("y", 9.5)
      .attr("dy", "0.32em")
      .text(function(d) { return d; });
});

</script>
		</div></div></div> 
			</center>
			</div></div>	
		
			
			<div id="legend3">
								<h3></h3>

								<div class="legend3">
									<p class="country-name">
										<span class="key-dot queens"></span>${awayTeam}
									</p>
								</div>
								<div class="legend3">
									<p class="country-name">
										<span class="key-dot kings"></span>${homeTeam}
									</p>
								</div>
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

    