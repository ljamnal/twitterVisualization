package com.uottawa.twittervisual.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uottawa.twittervisual.business.CLFixture;
import com.uottawa.twittervisual.business.DbGeoMap;
import com.uottawa.twittervisual.business.LiveMatch;
import com.uottawa.twittervisual.business.MatchGeo;
import com.uottawa.twittervisual.model.DataPointsModel;
import com.uottawa.twittervisual.model.FixtureDetail;
import com.uottawa.twittervisual.model.Sentiment;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	CLFixture clFixture;

	@Autowired
	LiveMatch liveMatch;

	/**
	 * This method is called whenever the home page is called.
	 * 
	 * @param model
	 *            Model
	 * @param request
	 *            Http Request received
	 * @return The name of the jsp to open
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(ModelMap model, HttpServletRequest request) throws ServletException, IOException {

		System.out.println(new Date());
		// DbGeoMap dbGeo = new DbGeoMap();
		// Calling method to fetch the geo related data from MongoDB
		// dbGeo.geoMap();

		// Set the model object for information of matches for quarters, semis and
		// finals.
		Map<String, List<FixtureDetail>> fixtureList = clFixture.getFixtures();
		model.put("quarters", fixtureList.get("quarterfinal"));
		model.put("semis", fixtureList.get("semifinal"));
		model.put("finals", fixtureList.get("final"));
		System.out.println(new Date());
		return "index";
	}

	@RequestMapping(value = "/topTeams")
	public @ResponseBody ModelAndView topClubs() {

		return new ModelAndView("TopTeamsCL", "message", "done");
	}

	@RequestMapping(value = "/worldwideGeo")
	public @ResponseBody ModelAndView worldwideGeo() throws ServletException, IOException {
		System.out.println("Geo start");
		System.out.println(new Date());
		DbGeoMap dbGeo = new DbGeoMap();
		// Calling method to fetch the geo related data from MongoDB
		dbGeo.geoMap();

		System.out.println(new Date());
		return new ModelAndView("WorldwidePopularity", "message", "done");
	}

	@RequestMapping(value = "/matchSchedule")
	public @ResponseBody ModelAndView matchSchedule(ModelMap model, HttpServletRequest request) {

		Map<String, List<FixtureDetail>> fixtureList = clFixture.getFixtures();
		model.put("quarters", fixtureList.get("quarterfinal"));
		model.put("semis", fixtureList.get("semifinal"));
		model.put("finals", fixtureList.get("final"));
		return new ModelAndView("MatchSchedule", "model", model);
	}

	@RequestMapping(value = "/matchHighlight")
	public @ResponseBody ModelAndView matchHighlight(ModelMap model, HttpServletRequest request) {

		return new ModelAndView("matchHighlight", "model", model);
	}

	/**
	 * This method is called whenever user clicks on 'Historical Data' tab
	 * 
	 * @param decadeId:
	 *            used to specify the 5-year period to view the CL summary
	 * @param model:
	 *            Used to set the value of the decadeID and send it to jsp page
	 * @return the name of the jsp to open along with the set model
	 */
	@RequestMapping(value = "/historicalData")
	public @ResponseBody ModelAndView historicalData(@RequestParam("decadeId") int decadeId, ModelMap model) {

		model.put("decadeId", decadeId);
		return new ModelAndView("FinalistHistory", "message", "done");
	}

	/**
	 * This method is called to fetch real time data every 30 seconds.
	 * 
	 * @param time:
	 *            specifies the minute of the match for which the detail is
	 *            requested
	 * @param matchId:
	 *            specifies the id of the match clicked.
	 * @param homeTeamId:
	 *            specifies the id of the home team
	 * @param awayTeamId:
	 *            specifies the id of the away team
	 * @param normalize:
	 *            specifies the mormalized value of the sentiment (range from -1 to
	 *            1)
	 * @param model:
	 *            all the details are set to model and sent to the liveChart.jsp
	 * @return: the name of the jsp to open
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/liveData", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String liveDataRealTime(@RequestParam("time") float time, @RequestParam("matchId") int matchId,
			@RequestParam("homeTeamId") int homeTeamId, @RequestParam("awayTeamId") int awayTeamId,
			@RequestParam("normalize") float normalize, ModelMap model) throws JsonProcessingException {

		String json = "";
		// to fetch match data if the match is currently ongoing
		if (time <= 100) {
			DataPointsModel m = liveMatch.getRealTimeMatchDetails(matchId, homeTeamId, awayTeamId, time, normalize);
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(m);
		}
		return json;
	}

	/**
	 * This method is called to populate date for real time chart and send the data
	 * to LiveChart.jsp page
	 * 
	 * @param matchId:
	 *            id of the match
	 * @param homeTeamId:
	 *            id of the home team
	 * @param awayTeamId:
	 *            id of the away team
	 * @param homeTeamName:
	 *            name of the home team
	 * @param awayTeamName:
	 *            name of the away team
	 * @param model:
	 *            model data passed to LiveChart jsp page
	 * @return: the name of the jsp page the data is sent to for chart rendering
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/fetch", method = RequestMethod.GET, produces = "application/json")
	public String fetchDetails(@RequestParam("matchId") int matchId, @RequestParam("homeTeamId") int homeTeamId,
			@RequestParam("awayTeamId") int awayTeamId, @RequestParam("homeTeam") String homeTeamName,
			@RequestParam("awayTeam") String awayTeamName, @RequestParam("stadium") String stadium, ModelMap model)
			throws ServletException, IOException {

		Date d = new Date();
		Sentiment sentiment = liveMatch.getMatchDetails(matchId, homeTeamId, awayTeamId);
		System.out.println("Live match:");
		System.out.println(d);
		System.out.println(new Date());

		int max = sentiment.getHomeCount();
		int averageSentimentMax = sentiment.getAwayCount();

		float normalize = ((float) max) * 1.2f;
		float normalizeAverage = ((float) averageSentimentMax) * 1.2f;

		// data based on matchId for geo chart
		Date date2 = new Date();
		MatchGeo geoMatch = new MatchGeo();
		geoMatch.getMatchWiseTweetDetails(matchId, homeTeamId, awayTeamId, homeTeamName, awayTeamName);
		System.out.println("Geo:");
		System.out.println(date2);
		System.out.println(new Date());

		String fileName = "C:\\test\\Match" + matchId + ".csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		List<DataPointsModel> dps1 = new ArrayList<>();
		List<DataPointsModel> dps2 = new ArrayList<>();
		List<DataPointsModel> dps3 = new ArrayList<>();
		List<DataPointsModel> dps4 = new ArrayList<>();

		try {

			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String[] country = line.split(cvsSplitBy);

				DataPointsModel d1 = new DataPointsModel();
				DataPointsModel d2 = new DataPointsModel();

				float time = Float.parseFloat(country[0]);

				int team1 = Integer.parseInt(country[1]);
				// team1 = team1 - 2 * team1;

				int team2 = Integer.parseInt(country[2]);
				// team2 = team2 - 2 * team2;

				float y1 = ((float) team1) / normalize;
				float y2 = ((float) team2) / normalize;
				d1.setX(time);
				d1.setY(y1);

				d2.setX(time);
				d2.setY(y2);

				dps1.add(d1);
				dps2.add(d2);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		String fileName2 = "C:\\test\\Average" + matchId + ".csv";

		try {

			br = new BufferedReader(new FileReader(fileName2));
			while ((line = br.readLine()) != null) {
				String[] country = line.split(cvsSplitBy);

				DataPointsModel d3 = new DataPointsModel();
				DataPointsModel d4 = new DataPointsModel();

				float time = Float.parseFloat(country[0]);

				int team1 = Integer.parseInt(country[1]);
				// team1 = team1 - 2 * team1;

				int team2 = Integer.parseInt(country[2]);
				// team2 = team2 - 2 * team2;

				float y1 = ((float) team1) / normalizeAverage;
				float y2 = ((float) team2) / normalizeAverage;
				d3.setX(time);
				d3.setY(y1);

				d4.setX(time);
				d4.setY(y2);

				dps3.add(d3);
				dps4.add(d4);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		ObjectMapper mapper = new ObjectMapper();

		String json1 = "";
		json1 = mapper.writeValueAsString(dps1);

		String json2 = "";
		json2 = mapper.writeValueAsString(dps2);

		String json3 = "";
		json3 = mapper.writeValueAsString(dps3);

		String json4 = "";
		json4 = mapper.writeValueAsString(dps4);

		// write the data inot model which will be sent to jsp page
		model.put("json1", json1);
		model.put("json2", json2);
		model.put("json3", json3);
		model.put("json4", json4);
		model.put("matchId", matchId);
		model.put("homeTeamId", homeTeamId);
		model.put("awayTeamId", awayTeamId);
		model.put("homeTeam", homeTeamName);
		model.put("awayTeam", awayTeamName);
		model.put("normalize", normalize);
		model.put("stadium", stadium);

		System.out.println(d);
		System.out.println(new Date());

		return "MatchPage";
	}

}