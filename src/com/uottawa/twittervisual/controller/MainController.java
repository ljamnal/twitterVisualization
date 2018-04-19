package com.uottawa.twittervisual.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
import com.uottawa.twittervisual.model.DataPointsModel;
import com.uottawa.twittervisual.model.FixtureDetail;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	CLFixture clFixture;
	
	@Autowired
	LiveMatch liveMatch;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(ModelMap model, HttpServletRequest request) throws ServletException, IOException {
		DbGeoMap dbGeo = new DbGeoMap();
		dbGeo.geoMap();

		Map<String, List<FixtureDetail>> fixtureList = clFixture.getFixtures();
		model.put("quarters", fixtureList.get("quarterfinal"));
		model.put("semis", fixtureList.get("semifinal"));
		model.put("finals", fixtureList.get("final"));

		return "index";
	}
	
	@RequestMapping(value = "/topClubs")
	public @ResponseBody ModelAndView topClubs() {

		
		return new ModelAndView("top","message","done");
	}
	
	@RequestMapping(value = "/liveData", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String liveDataRealTime(@RequestParam("time") float time, @RequestParam("matchId") int matchId,
			@RequestParam("homeTeamId") int homeTeamId, @RequestParam("awayTeamId") int awayTeamId, ModelMap model)
			throws JsonProcessingException {

		String json = "";
		if (time <= 100) {

			DataPointsModel m = liveMatch.getRealTimeMatchDetails(matchId, homeTeamId, awayTeamId, time);

			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(m);
		}
		return json;
	}

	@RequestMapping(value = "/fetch", method = RequestMethod.GET, produces = "application/json")
	public String fetchDetails(@RequestParam("matchId") int matchId, @RequestParam("homeTeamId") int homeTeamId,
			@RequestParam("awayTeamId") int awayTeamId, ModelMap model) throws JsonProcessingException {

		liveMatch.getMatchDetails(matchId, homeTeamId, awayTeamId);

		String fileName = "E:\\test\\Match" + matchId + ".csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		List<DataPointsModel> dps1 = new ArrayList<>();
		List<DataPointsModel> dps2 = new ArrayList<>();

		try {

			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String[] country = line.split(cvsSplitBy);

				DataPointsModel d1 = new DataPointsModel();
				DataPointsModel d2 = new DataPointsModel();

				float time = Float.parseFloat(country[0]);

				int team1 = Integer.parseInt(country[1]);
				team1 = team1 - 2 * team1;

				int team2 = Integer.parseInt(country[2]);
				team2 = team2 - 2 * team2;

				d1.setX(time);
				d1.setY(team1);

				d2.setX(time);
				d2.setY(team2);

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

		ObjectMapper mapper = new ObjectMapper();

		String json1 = "";
		json1 = mapper.writeValueAsString(dps1);

		String json2 = "";
		json2 = mapper.writeValueAsString(dps2);

		model.put("json1", json1);
		model.put("json2", json2);

		return "LiveChart";
	}
	
}