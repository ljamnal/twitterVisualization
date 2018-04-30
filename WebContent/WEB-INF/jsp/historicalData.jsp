<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8" />

    <link data-require="normalize@*" data-semver="3.0.1" rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/normalize/3.0.1/normalize.min.css" />
    
    <script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	
	<script type="text/javascript"
	src="<c:url value="/resources/js/canvasjs.min.js" />"></script>

    <style>
    
    
    
    .link {
        fill: none;
        stroke: #555;
        stroke-opacity: 0.4;
        stroke-width: 1px;
    }
    text {
        font-family: "Arial Black", Gadget, sans-serif;
        fill: black;
        font-weight: bold;
        font-size: 14px
    }

    .xAxis .tick text{
        fill: black;
    }
    .grid .tick line{
        stroke: grey;
        stroke-dasharray: 5, 10;
        opacity: 0.7;
    }
    .grid path{
        stroke-width: 0;
    }

    .node circle {
        fill: #999;
    }
    .node--internal circle {
        fill: #555;
    }
    .node--internal text {
        font-size: 16px;
        text-shadow: 0 2px 0 #fff, 0 -2px 0 #fff, 2px 0 0 #fff, -2px 0 0 #fff;
    }
    .node--leaf text {
        fill: white;
    }
    .ballG text {
        fill: white;
    }

    .shadow {
        -webkit-filter: drop-shadow( -1.5px -1.5px 1.5px #000 );
        filter: drop-shadow( -1.5px -1.5px 1.5px #000 );
    }
    
    
#chart {
	height: 360px;
	margin: 0 auto; /* NEW */
	position: relative;
	width: 360px;
}

.tooltip2 {
	background: #eee;
	box-shadow: 0 0 5px #999999;
	color: #333;
	display: none;
	font-size: 12px;
	left: 130px;
	padding: 10px;
	position: absolute;
	text-align: center;
	top: 95px;
	width: 280px;
	z-index: 10;
}

.legend {
	font-size: 12px;
}

rect {
	cursor: pointer; /* NEW */
	stroke-width: 2;
}

rect.disabled { /* NEW */
	fill: transparent !important; /* NEW */
} /* NEW */
h1 { /* NEW */
	font-size: 14px; /* NEW */
	text-align: center; /* NEW */
} /* NEW */


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
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
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
.dropdown-content a:hover {background-color: #f1f1f1}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {
    display: block;
}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {
    background-color: #3e8e41;
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
    	
    	
<div class="setmargin">
          <div class="col-xl-12">
                <div class="card" style="height: 700px;margin-top:20px">
<center><h3> Year wise summary</h3></center>
               <br>


<center>
<div class="dropdown">
  <button class="dropbtn">Select 5-year period</button>
  <div class="dropdown-content">
    <a href="historicalData?decadeId=1">2011-2015</a>
    <a href="historicalData?decadeId=2">2006-2010</a>
    <a href="historicalData?decadeId=3">2001-2005</a>
    <a href="historicalData?decadeId=4">1996-2000</a>
    <a href="historicalData?decadeId=5">1991-1995</a>
  </div>
</div>

</center>
<br><br>


                     <svg width="960" height="500"></svg>
 
     <script src="https://d3js.org/d3.v4.min.js"></script>
<script>

    // main svg
    var svg = d3.select("svg"),
            width = +svg.attr("width"),
            height = +svg.attr("height"),
            g = svg.append("g").attr("transform", "translate(20,0)");       // move right 20px.

    // x-scale and x-axis
    var experienceName = ["0","1","2","3","4","5"];
    var formatSkillPoints = function (d) {
        return experienceName[d % 6];
    }
    var xScale =  d3.scaleLinear()
            .domain([0,5])
            .range([0, 400]);

    var xAxis = d3.axisTop()
            .scale(xScale)
            .ticks(5)
            .tickFormat(formatSkillPoints);

    // Setting up a way to handle the data
    var tree = d3.cluster()                 // This D3 API method setup the Dendrogram datum position.
            .size([height, width - 460])    // Total width - bar chart width = Dendrogram chart width
            .separation(function separate(a, b) {
                return a.parent == b.parent            // 2 levels tree grouping for category
                || a.parent.parent == b.parent
                || a.parent == b.parent.parent ? 0.4 : 0.8;
            });

    var stratify = d3.stratify()            // This D3 API method gives cvs file flat data array dimensions.
            .parentId(function(d) { return d.id.substring(0, d.id.lastIndexOf(".")); });

    d3.csv("<c:url value='/resources/data/TreeData_${decadeId}.csv'/>", row, function(error, data) {
        if (error) throw error;

        var root = stratify(data);
        tree(root);

        // Draw every datum a line connecting to its parent.
        var link = g.selectAll(".link")
                .data(root.descendants().slice(1))
                .enter().append("path")
                .attr("class", "link")
                .attr("d", function(d) {
                    return "M" + d.y + "," + d.x
                            + "C" + (d.parent.y + 100) + "," + d.x
                            + " " + (d.parent.y + 100) + "," + d.parent.x
                            + " " + d.parent.y + "," + d.parent.x;
                });

        // Setup position for every datum; Applying different css classes to parents and leafs.
        var node = g.selectAll(".node")
                .data(root.descendants())
                .enter().append("g")
                .attr("class", function(d) { return "node" + (d.children ? " node--internal" : " node--leaf"); })
                .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });

        // Draw every datum a small circle.
        node.append("circle")
                .attr("r", 4);

        // Setup G for every leaf datum.
        var leafNodeG = g.selectAll(".node--leaf")
                .append("g")
                .attr("class", "node--leaf-g")
                .attr("transform", "translate(" + 8 + "," + -13 + ")");

        leafNodeG.append("rect")
                .attr("class","shadow")
                .style("fill", function (d) {return d.data.color;})
                .attr("width", 2)
                .attr("height", 30)
                .attr("rx", 2)
                .attr("ry", 2)
                .transition()
                    .duration(800)
                    .attr("width", function (d) {return xScale(d.data.value);});

        leafNodeG.append("text")
                .attr("dy", 19.5)
                .attr("x", 8)
                .style("text-anchor", "start")
                .text(function (d) {
                    return d.data.id.substring(d.data.id.lastIndexOf(".") + 1);
                });

        // Write down text for every parent datum
        var internalNode = g.selectAll(".node--internal");
        internalNode.append("text")
                .attr("y", -10)
                .style("text-anchor", "middle")
                .text(function (d) {
                    return d.data.id.substring(d.data.id.lastIndexOf(".") + 1);
                });

        // Attach axis on top of the first leaf datum.
        var firstEndNode = g.select(".node--leaf");
            firstEndNode.insert("g")
                    .attr("class","xAxis")
                    .attr("transform", "translate(" + 7 + "," + -14 + ")")
                    .call(xAxis);

            // tick mark for x-axis
            firstEndNode.insert("g")
                    .attr("class", "grid")
                    .attr("transform", "translate(7," + (height - 15) + ")")
                    .call(d3.axisBottom()
                            .scale(xScale)
                            .ticks(5)
                            .tickSize(-height, 0, 0)
                            .tickFormat("")
                    );

        // Emphasize the y-axis baseline.
        svg.selectAll(".grid").select("line")
                .style("stroke-dasharray","20,1")
                .style("stroke","black");

        // The moving ball
        var ballG = svg.insert("g")
                .attr("class","ballG")
                .attr("transform", "translate(" + 1100 + "," + height/2 + ")");
        ballG.insert("circle")
                .attr("class","shadow")
                .style("fill","steelblue")
                .attr("r", 5);
        ballG.insert("text")
                .style("text-anchor", "middle")
                .attr("dy",5)
                .text("0.0");

        // Animation functions for mouse on and off events.
        d3.selectAll(".node--leaf-g")
                .on("mouseover", handleMouseOver)
                .on("mouseout", handleMouseOut);

        function handleMouseOver(d) {
            var leafG = d3.select(this);

            leafG.select("rect")
                    .attr("stroke","#4D4D4D")
                    .attr("stroke-width","2");


            var ballGMovement = ballG.transition()
                    .duration(400)
                    .attr("transform", "translate(" + (d.y
                            + xScale(d.data.value) + 90) + ","
                            + (d.x + 1.5) + ")");

            ballGMovement.select("circle")
                    .style("fill", d.data.color)
                    .attr("r", 18);

            ballGMovement.select("text")
                    .delay(300)
                    .text(Number(d.data.value).toFixed(1));
        }
        function handleMouseOut() {
            var leafG = d3.select(this);

            leafG.select("rect")
                    .attr("stroke-width","0");
        }

    });

    function row(d) {
        return {
            id: d.id,
            value: +d.value,
            color: d.color
        };
    }
</script>	
               
                     
                     
                     
     
       </div>
       </div></div>



<div class="setmargin">
          <div class="col-xl-12">
                <div class="card" style="height: 600px;margin-top:20px">
<center><h3>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp Teams with most CL titles</h3></center>
               <br>
               
               
               <div id="chart"></div>
	<script src="<c:url value="https://d3js.org/d3.v4.min.js" />"
		data-semver="4.0.0" data-require="d3@*"></script>
	<script>

	draw();

	
    function draw() {
        var width = 500;
        var height = 500;
        var radius = Math.min(width, height) / 2;
        var donutWidth = 75;
        var legendRectSize = 18;
        var legendSpacing = 4;

        var color = d3.scaleOrdinal(d3.schemeCategory10);

        var svg = d3.select('#chart')
          .append('svg')
          .attr('width', width)
          .attr('height', height)
          .append('g')
          .attr('transform', 'translate(' + (width / 2) + 
            ',' + (height / 2) + ')');

        var arc = d3.arc()
          .innerRadius(radius - donutWidth)
          .outerRadius(radius);

        var pie = d3.pie()
          .value(function(d) { return d.goals; })
          .sort(null);

        var tooltip2 = d3.select('#chart')
          .append('div')
          .attr('class', 'tooltip2');
        
        tooltip2.append('div')
          .attr('class', 'club');

        tooltip2.append('div')
          .attr('class', 'goals');

        tooltip2.append('div')
          .attr('class', 'year');

        d3.csv("<c:url value='/resources/data/TopTeams.csv'/>", function(error, dataset) {
          dataset.forEach(function(d) {
            d.goals = +d.goals;
            d.enabled = true;                                         // NEW
          });

          var path = svg.selectAll('path')
            .data(pie(dataset))
            .enter()
            .append('path')
            .attr('d', arc)
            .attr('fill', function(d, i) { 
              return color(d.data.club); 
            })                                                        // UPDATED (removed semicolon)
            .each(function(d) { this._current = d; });                // NEW

          path.on('mouseover', function(d) {
            var total = d3.sum(dataset.map(function(d) {
              return (d.enabled) ? d.goals : 0;                       // UPDATED
            }));
            var percent = Math.round(1000 * d.data.goals / total) / 10;
            tooltip2.select('.club').html(d.data.club);
            tooltip2.select('.goals').html(d.data.goals + ' titles'); 
            tooltip2.select('.year').html(d.data.year); 
            //tooltip2.select('.percent').html(percent + '%'); 
            tooltip2.style('display', 'block');
          });
          
          path.on('mouseout', function() {
            tooltip2.style('display', 'none');
          });

          /* OPTIONAL 
          path.on('mousemove', function(d) {
            tooltip2.style('top', (d3.event.pageY + 10) + 'px')
              .style('left', (d3.event.pageX + 10) + 'px');
          });
          */
            
          var legend = svg.selectAll('.legend')
            .data(color.domain())
            .enter()
            .append('g')
            .attr('class', 'legend')
            .attr('transform', function(d, i) {
              var height = legendRectSize + legendSpacing;
              var offset =  height * color.domain().length / 2;
              var horz = -2 * legendRectSize;
              var vert = i * height - offset;
              return 'translate(' + horz + ',' + vert + ')';
            });

          legend.append('rect')
            .attr('width', legendRectSize)
            .attr('height', legendRectSize)                                   
            .style('fill', color)
            .style('stroke', color)                                   // UPDATED (removed semicolon)
            .on('click', function(label) {                            // NEW
              var rect = d3.select(this);                             // NEW
              var enabled = true;                                     // NEW
              var totalEnabled = d3.sum(dataset.map(function(d) {     // NEW
                return (d.enabled) ? 1 : 0;                           // NEW
              }));                                                    // NEW
              
              if (rect.attr('class') === 'disabled') {                // NEW
                rect.attr('class', '');                               // NEW
              } else {                                                // NEW
                if (totalEnabled < 2) return;                         // NEW
                rect.attr('class', 'disabled');                       // NEW
                enabled = false;                                      // NEW
              }                                                       // NEW

              pie.value(function(d) {                                 // NEW
                if (d.club === label) d.enabled = enabled;           // NEW
                return (d.enabled) ? d.goals : 0;                     // NEW
              });                                                     // NEW

              path = path.data(pie(dataset));                         // NEW

              path.transition()                                       // NEW
                .duration(750)                                        // NEW
                .attrTween('d', function(d) {                         // NEW
                  var interpolate = d3.interpolate(this._current, d); // NEW
                  this._current = interpolate(0);                     // NEW
                  return function(t) {                                // NEW
                    return arc(interpolate(t));                       // NEW
                  };                                                  // NEW
                });                                                   // NEW
            });                                                       // NEW
            
          legend.append('text')
            .attr('x', legendRectSize + legendSpacing)
            .attr('y', legendRectSize - legendSpacing)
            .text(function(d) { return d; });

        });
    	
    }

    </script>
     
   </div></div></div>
   </div>
    

</body>
</html>

<!-- "<c:url value="/resources/pointsTable/pointsTable_${fileName}.csv" />" -->
