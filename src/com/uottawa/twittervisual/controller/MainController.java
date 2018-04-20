package com.uottawa.twittervisual.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uottawa.twittervisual.business.DbGeoMap;
import com.uottawa.twittervisual.model.DataPointsModel;
import com.uottawa.twittervisual.model.Timeline;

@Controller
@RequestMapping("/")
public class MainController {
	
	static int count = 0;
		
	@RequestMapping(value = "/liveData", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String liveData(ModelMap model) throws JsonProcessingException {

		count++;
		String json = "";
		if(count<=30) {
			DataPointsModel m = new DataPointsModel();
			m.setX(12);
			m.setY(18);
			
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(m);
		}
		// model.put("datax", 12);
		// model.put("datay", 18);		
		System.out.println(json);
		
		return json;
	}


	
	@RequestMapping(value = "/live", method = RequestMethod.GET)
	public String livePage(ModelMap model, HttpServletRequest request) throws JsonProcessingException {
	return "LiveChart";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(ModelMap model, HttpServletRequest request) throws ServletException, IOException {

		DbGeoMap dbGeo = new DbGeoMap();
		dbGeo.geoMap();
		dbGeo.TeamTweetCount();
		
		return "index";
	}

	
	
	@RequestMapping(value = "/topClubs")
	public @ResponseBody ModelAndView topClubs() {

		
		return new ModelAndView("top","message","done");
	}
	
	
}