<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Timeline | Templates</title>

<!-- load handlebars for templating, and create a template -->
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.5/handlebars.min.js"></script>
<script id="item-template" type="text/x-handlebars-template">
    <table class="score">
      <tr>
        <td colspan="3" class="description">{{description}}</td>
      </tr>
      <tr>
        <td>{{player1}}</td>
        <th>{{score1}} - {{score2}}</th>
        <td>{{player2}}</td>
      </tr>
      <tr>
        <td><img src="http://flagpedia.net/data/flags/mini/{{abbr1}}.png" width="31" height="20" alt="{{abbr1}}"></td>
        <th></th>
        <td><img src="http://flagpedia.net/data/flags/mini/{{abbr2}}.png" width="31" height="20" alt="{{abbr2}}"></td>
      </tr>
    </table>
  </script>
<script src="<c:url value="/resources/js/vis.js" />"></script>
<link
	href="<c:url value="/resources/css/vis-timeline-graph2d.min.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/timeline_custom.css" />"
	rel="stylesheet" type="text/css" />
</head>
<body>


	<div id="visualization"></div>


	<script type="text/javascript">
		// create a handlebars template
		var source = document.getElementById('item-template').innerHTML;
		var template = Handlebars.compile(document
				.getElementById('item-template').innerHTML);

		// DOM element where the Timeline will be attached
		var container = document.getElementById('visualization');

		var items = ${fixtureList};

		var options = {
			// specify a template for the items
			template : template
		};

		// Create a Timeline
		var timeline = new vis.Timeline(container, items, options);
	</script>
	<form>
		<select name="season" onchange="showFixtures(this.value)">
			<option value="">Select season:</option>
			<option value="2017-2018">2017-2018</option>
			<option value="2016-2017">2016-2017</option>
		</select>
	</form>
	
</body>
</html>
