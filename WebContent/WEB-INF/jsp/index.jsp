<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    
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
                        <a href="index.html"> <i class="menu-icon fa fa-dashboard"></i>Dashboard </a>
                    </li>
                    <h3 class="menu-title">UI elements</h3><!-- /.menu-title -->
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-laptop"></i>Geographical analysis</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-puzzle-piece"></i><a href="ui-buttons.html">Buttons</a></li>
                            <li><i class="fa fa-id-badge"></i><a href="ui-badges.html">Badges</a></li>
                            <li><i class="fa fa-bars"></i><a href="ui-tabs.html">Tabs</a></li>
                            <li><i class="fa fa-share-square-o"></i><a href="ui-social-buttons.html">Social Buttons</a></li>
                            <li><i class="fa fa-id-card-o"></i><a href="ui-cards.html">Cards</a></li>
                            <li><i class="fa fa-exclamation-triangle"></i><a href="ui-alerts.html">Alerts</a></li>
                            <li><i class="fa fa-spinner"></i><a href="ui-progressbar.html">Progress Bars</a></li>
                            <li><i class="fa fa-fire"></i><a href="ui-modals.html">Modals</a></li>
                            <li><i class="fa fa-book"></i><a href="ui-switches.html">Switches</a></li>
                            <li><i class="fa fa-th"></i><a href="ui-grids.html">Grids</a></li>
                            <li><i class="fa fa-file-word-o"></i><a href="ui-typgraphy.html">Typography</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Top tweets</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="<c:url value="topClubs.html"/>">Trending tweets</a></li>
                            <li><i class="fa fa-table"></i><a href="tables-data.html">Data Table</a></li>
                        </ul>
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
                        <h1>Champions League Dashboard</h1>
                         
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="page-header float-right">
                   
                </div>
            </div>
        </div>



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

<div id="quarter" class="w3-container w3-display-container city">

                <c:forEach var="fixture" items="${quarters}">
                  <div class="gallery">
                    <a href=
                      "fetch?matchId=${fixture.matchId}&homeTeamId=${fixture.homeTeamId}&awayTeamId=${fixture.awayTeamId}&homeTeam=${fixture.homeTeamName}&awayTeam=${fixture.awayTeamName}">
                      <img
                      src="<c:url value="/resources/img/team/england_2013_winner.jpg"/>"
                      height="600" width="600" />
                    </a>
                    <div class="desc">${fixture.teams}</div>
                    <div class="desc">${fixture.matchTime}</div>
                    <div class="desc">${fixture.stadium}</div>
                  </div>
                </c:forEach>

              </div>

              <div id="semi" class="w3-container w3-display-container city"
                style="display: none">

                <c:forEach var="fixture" items="${semis}">
                  <div class="gallery">
                    <a href=
                      "fetch?matchId=${fixture.matchId}&homeTeamId=${fixture.homeTeamId}&awayTeamId=${fixture.awayTeamId}&homeTeam=${fixture.homeTeamName}&awayTeam=${fixture.awayTeamName}">
                      <img
                      src="<c:url value="/resources/img/team/england_2013_winner.jpg"/>"
                      height="600" width="600" />
                    </a>
                    <div class="desc">${fixture.teams}</div>
                    <div class="desc">${fixture.matchTime}</div>
                    <div class="desc">${fixture.stadium}</div>
                  </div>
                </c:forEach>
              </div>



              <div id="final" class="w3-container w3-display-container city"
                style="display: none">
                <c:forEach var="fixture" items="${finals}">
                  <div class="gallery">
                    <a
                      href="fetch?matchId=${fixture.matchId}&homeTeamId=${fixture.homeTeamId}&awayTeamId=${fixture.awayTeamId}&homeTeam=${fixture.homeTeamName}&awayTeam=${fixture.awayTeamName}">
                      <img
                      src="<c:url value="/resources/img/team/england_2013_winner.jpg"/>"
                      height="600" width="600" />
                    </a>
                    <div class="desc">${fixture.teams}</div>
                    <div class="desc">${fixture.matchTime}</div>
                    <div class="desc">${fixture.stadium}</div>
                  </div>
                </c:forEach>
              </div>

</div></div></div></div>
</div>



     <div class="content mt-3">


          <div class="col-xl-12">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-4">
                                <h4 class="card-title mb-0">Worldwide Tweets</h4>
                                <div class="small text-muted">Based on sentiments</div>
                            </div>
                            <!--/.col-->
                           


                        </div><!--/.row-->
                        

                 <div id="container"></div>
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
      'value': 'density'
    });
    var series = map.choropleth(density_data);

    series.labels(false);

    series.hovered()
      .fill('#f48fb1')
      .stroke(anychart.color.darken('#f48fb1'));

    series.selected()
      .fill('#c2185b')
      .stroke(anychart.color.darken('#c2185b'));

    series.tooltip()
      .useHtml(true)
      .format(function() {
        return '<span style="color: #d9d9d9">Negative</span>: ' +
          parseFloat(this.value).toLocaleString() + ' tweets <br/>' +
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
        to: 30
      },
      {
        from: 30,
        to: 50
      },
      {
        from: 50,
        to: 100
      },
      {
        from: 100,
        to: 200
      },
      {
        from: 200,
        to: 300
      },
      {
        from: 300,
        to: 500
      },
      {
        from: 500,
        to: 1000
      },
      {
        greater: 1000
      }
    ]);
    scale.colors(['#81d4fa', '#4fc3f7', '#29b6f6', '#039be5', '#0288d1', '#0277bd', '#01579b', '#014377', '#000000']);

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
          name = 'More than ' + range.start;
        } else {
          name = 'Less than ' + range.end;
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
            </div>






        </div> <!-- .content -->
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
