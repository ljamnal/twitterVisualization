<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    
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
  
  
    <style type="text/css">  
    
    .setmargin{
    margin-top: 500px;
    }
    
    #container {
    height: 500px; 
    min-width: 310px; 
    max-width: 800px; 
    margin: 0 auto; 
}
.loading {
    margin-top: 10em;
    text-align: center;
    color: gray;
}
    
     div.gallery {
    margin: 5px;
    border: 1px solid #ccc;
    float: left;
    width: 180px;
}


div.gallery:hover {
    border: 1px solid #777;
}

div.gallery img {
    width: 100%;
    height: auto;
}

div.desc {
    padding: 15px;
    text-align: center;
}

    </style>

    <link rel="apple-touch-icon" href="apple-icon.png">
    <link rel="shortcut icon" href="favicon.ico">

    <link rel="stylesheet" href="resources/assets/css/normalize.css">
    <link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/assets/css/themify-icons.css">
    <link rel="stylesheet" href="resources/assets/css/flag-icon.min.css">
    <link rel="stylesheet" href="resources/assets/css/cs-skin-elastic.css">
    <!-- <link rel="stylesheet" href="assets/css/bootstrap-select.less"> -->
    <link rel="stylesheet" href="resources/assets/scss/style.css">
    <link href="resources/assets/css/lib/vector-map/jqvmap.min.css" rel="stylesheet">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>

    <!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->
    
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    

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
					dataPoints : dps
				}, {
					type : "line",
					name : '${awayTeam}',
					showInLegend: true,
					dataPoints : dps2
				} ]
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
	</script>
	
	

        <!-- Left Panel -->

    <aside id="left-panel" class="left-panel">
        <nav class="navbar navbar-expand-sm navbar-default">

            <div class="navbar-header">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand" href="./"><img src="resources/images/uottawa_logo2.png" alt="Logo"></a>
                <a class="navbar-brand hidden" href="./"><img src="resources/images/logo2.png" alt="Logo"></a>
            </div>

            <div id="main-menu" class="main-menu collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="./"> <i class="menu-icon fa fa-dashboard"></i>Dashboard </a>
                    </li>
                   <h3 class="menu-title">SOCCER STATS</h3><!-- /.menu-title -->
                   
                    <li class="menu-item-has-children dropdown">
                        <a href="<c:url value="topClubs.html"/>" class="dropdown-toggle" > <i class="menu-icon fa fa-table"></i>Tweets (Team-wise)</a>
                       
                    </li>
                    
                     </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="<c:url value="historicalData?decadeId=1"/>" class="dropdown-toggle" > <i class="menu-icon fa fa-table"></i>Historical Facts</a>
                       
                    </li>
                    
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
    </aside><!-- /#left-panel -->

    <!-- Left Panel -->

    <!-- Right Panel -->

    <div id="right-panel" class="right-panel">

      

        <div class="breadcrumbs">
            <div class="col-sm-4">
                <div class="page-header float-left">
                    <div class="page-title">
                       <h1><b>Champions League Dashboard</b></h1>
                         
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="page-header float-right">
                   
                </div>
            </div>
        </div>



								<div class="content mt-3">
			<div class="animated fadeIn">

				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-header">
								<h4>${homeTeam}&nbsp;&nbsp;vs&nbsp;&nbsp;${awayTeam}</h4>
							</div>
							<div class="Vect">
								<div id="chartContainer"></div>
							</div>
						</div>
						<!-- /# card -->
					</div>
					<!-- /# column -->



				</div>
				<!-- /# row -->


			</div>
			<!-- .animated -->
		</div>






		<!-- .content -->

<div class="setmargin">
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
    scale.colors(['#ff9933', '#339966']);

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
<center>
<div class="col-xl-12">
                <div class="card">
                    <div class="card-body">

<h3>Tweets comparison</h3><br>
<svg width="300" height="500"></svg>
<script src="https://d3js.org/d3.v4.min.js"></script>
<script>

var svg = d3.select("svg"),
    margin = {top: 20, right: 20, bottom: 30, left: 40},
    width = +svg.attr("width") - margin.left - margin.right,
    height = +svg.attr("height") - margin.top - margin.bottom,
    g = svg.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");

var x = d3.scaleBand()
    .rangeRound([0, width])
    .paddingInner(0.05)
    .align(0.1);

var y = d3.scaleLinear()
    .rangeRound([height, 0]);

var z = d3.scaleOrdinal()
    .range(["#ff8c00", "#339966"]);

d3.csv("resources/data/TweetsSummaryMatch.csv", function(d, i, columns) {
  for (i = 1, t = 0; i < columns.length; ++i) t += d[columns[i]] = +d[columns[i]];
  d.total = t;
  return d;
}, function(error, data) {
  if (error) throw error;

  var keys = data.columns.slice(1);

  data.sort(function(a, b) { return b.total - a.total; });
  x.domain(data.map(function(d) { return d.State; }));
  y.domain([0, d3.max(data, function(d) { return d.total; })]).nice();
  z.domain(keys);

  g.append("g")
    .selectAll("g")
    .data(d3.stack().keys(keys)(data))
    .enter().append("g")
      .attr("fill", function(d) { return z(d.key); })
    .selectAll("rect")
    .data(function(d) { return d; })
    .enter().append("rect")
      .attr("x", function(d) { return x(d.data.State); })
      .attr("y", function(d) { return y(d[1]); })
      .attr("height", function(d) { return y(d[0]) - y(d[1]); })
      .attr("width", x.bandwidth());
  

  g.append("g")
      .attr("class", "axis")
      .attr("transform", "translate(0," + height + ")")
      .call(d3.axisBottom(x));

  g.append("g")
      .attr("class", "axis")
      .call(d3.axisLeft(y).ticks(null, "s"))
    .append("text")
      .attr("x", 2)
      .attr("y", y(y.ticks().pop()) + 0.5)
      .attr("dy", "0.32em")
      .attr("fill", "#000")
      .attr("font-weight", "bold")
      .attr("text-anchor", "start")
      .text("Count");

  var legend = g.append("g")
      .attr("font-family", "sans-serif")
      .attr("font-size", 10)
      .attr("text-anchor", "end")
    .selectAll("g")
    .data(keys.slice().reverse())
    .enter().append("g")
      .attr("transform", function(d, i) { return "translate(0," + i * 20 + ")"; });

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

</div>	<!--set margin -->










    </div><!-- /#right-panel -->





   
    <!-- Right Panel -->

    <script src="resources/assets/js/vendor/jquery-2.1.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="resources/assets/js/plugins.js"></script>
    <script src="resources/assets/js/main.js"></script>


    <script src="resources/assets/js/lib/chart-js/Chart.bundle.js"></script>
    <script src="resources/assets/js/dashboard.js"></script>
    <script src="resources/assets/js/widgets.js"></script>
    <script src="resources/assets/js/lib/vector-map/jquery.vmap.js"></script>
    <script src="resources/assets/js/lib/vector-map/jquery.vmap.min.js"></script>
    <script src="resources/assets/js/lib/vector-map/jquery.vmap.sampledata.js"></script>
    <script src="resources/assets/js/lib/vector-map/country/jquery.vmap.world.js"></script>
    
    

	<script
		src="resources/assets/js/lib/vector-map/country/jquery.vmap.algeria.js"></script>
	<!-- scripit init-->
	<script
		src="resources/assets/js/lib/vector-map/country/jquery.vmap.argentina.js"></script>
	<!-- scripit init-->
	<script
		src="resources/assets/js/lib/vector-map/country/jquery.vmap.brazil.js"></script>
	<!-- scripit init-->
	<script
		src="resources/assets/js/lib/vector-map/country/jquery.vmap.france.js"></script>
	<!-- scripit init-->
	<script
		src="resources/assets/js/lib/vector-map/country/jquery.vmap.germany.js"></script>
	<!-- scripit init-->
	<script
		src="resources/assets/js/lib/vector-map/country/jquery.vmap.greece.js"></script>
	<!-- scripit init-->
	<script
		src="resources/assets/js/lib/vector-map/country/jquery.vmap.iran.js"></script>
	<!-- scripit init-->
	<script
		src="resources/assets/js/lib/vector-map/country/jquery.vmap.iraq.js"></script>
	<!-- scripit init-->
	<script
		src="resources/assets/js/lib/vector-map/country/jquery.vmap.russia.js"></script>
	<!-- scripit init-->
	<script
		src="resources/assets/js/lib/vector-map/country/jquery.vmap.tunisia.js"></script>
	<!-- scripit init-->
	<script
		src="resources/assets/js/lib/vector-map/country/jquery.vmap.europe.js"></script>
	<!-- scripit init-->
	<script
		src="resources/assets/js/lib/vector-map/country/jquery.vmap.usa.js"></script>
	<!-- scripit init-->
	<script
		src="resources/assets/js/lib/vector-map/country/jquery.vmap.turkey.js"></script>
	<!-- scripit init-->
	<script src="resources/assets/js/lib/vector-map/vector.init.js"></script>



    
    
    
    <script>
        ( function ( $ ) {
            "use strict";

            jQuery( '#vmap' ).vectorMap( {
                map: 'world_en',
                backgroundColor: null,
                color: '#ffffff',
                hoverOpacity: 0.7,
                selectedColor: '#1de9b6',
                enableZoom: true,
                showTooltip: true,
                values: sample_data,
                scaleColors: [ '#1de9b6', '#03a9f5' ],
                normalizeFunction: 'polynomial'
            } );
        } )( jQuery );
    </script>
    

</body>
</html>
