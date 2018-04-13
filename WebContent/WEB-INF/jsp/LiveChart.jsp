<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/canvasjs.min.js" />"></script>
<div id='chartContainer'></div>

<script type="text/javascript">
	function crunchifyAjax() {

	}
	$(function() {
		var dps = [ {
			x : 1,
			y : 10
		} ]; //dataPoints.
		var dps2 = [ {
			x : 1,
			y : 10
		} ]; //dataPoints.

		var chart = new CanvasJS.Chart("chartContainer", {
			title : {
				text : "Live Chart"
			},
			data : [ {
				type : "line",
				dataPoints : dps
			}, {
				type : "line",
				dataPoints : dps2
			} ]
		});

		chart.render();

		var xVal = dps.length + 1;
		var yVal = 15;
		var yVal2 = 15;
		var updateInterval = 2000;

		var updateChart = function() {

			$.ajax({
				url : 'liveData',
				contentType : 'application/json',
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
					xVal++;

					chart.render();
				},
				error : function(xhr, status, error) {
					//var err = eval("(" + xhr.responseText + ")");
					//alert(xhr.responseText);
					alert(error + "\r\n" + xhr.statusText + "\r\n"
							+ xhr.responseText);
				}
			});

		};
		setInterval(function() {
			updateChart()
		}, updateInterval);
	});
</script>