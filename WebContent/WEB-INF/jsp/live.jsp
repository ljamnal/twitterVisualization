<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- FusionCharts core JavaScript file -->
<script src="<c:url value="/resources/js/fusioncharts.js" /> "></script>
</head>
<body>
	<!-- Chart Container -->
	<div id="chart-container">An awesome chart is on its way!</div>


</body>
<script>
FusionCharts.ready(function() { //FusionCharts Constructor
  var processorChart = new FusionCharts({
    //Chart Instance 
    // We have defined type, DOM container, dimensions, data format and data source here
    type: "realtimeline",
    renderAt: "chart-container",
    width: "100%",
    height: "450",
    dataFormat: "json",
    dataSource: {
      "chart": {
        //Data Stream URL will feed data to the chart
        "datastreamurl": "liveData",
        // frequency of fetching new data
        "refreshinterval": "2",
        "numDisplaySets" : 10,
        "canvasLeftMargin" : -900
        //Other Chart Configurations
      },
      "categories": [{
        "category": [{
          "label": "Start"
        }]
      }],
      "dataset": [{
        //For Processor A
        "color": "00dd00",
        "seriesname": "Processor A",
        "data": [{
          "value": "0"
        }]
      }, {
    	//For Processor B
          "color": "00dd00",
          "seriesname": "Processor B",
          "data": [{
            "value": "0"
          }]
      }]
    }
  }).render(); //Render Method
});
</script>
</html>