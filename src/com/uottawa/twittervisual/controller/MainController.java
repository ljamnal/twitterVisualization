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

	/*@ResponseBody
	@RequestMapping(value = "/liveData", method = RequestMethod.GET)
	public String liveData(ModelMap model, HttpServletRequest request) throws JsonProcessingException {
	
		if(count > 10) {
			return "";
		}
		if(count%2 == 0) {
			return "&label="+ (count++) + "&value=1|2";	
		} else {
			return "&label="+ (count++) + "&value=2|1";
		}
		
	}*/
	
	@RequestMapping(value = "/live", method = RequestMethod.GET)
	public String livePage(ModelMap model, HttpServletRequest request) throws JsonProcessingException {
	return "LiveChart";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(ModelMap model, HttpServletRequest request) throws JsonProcessingException {

		Timeline t1 = new Timeline();
		t1.setPlayer1("Brazil");
		t1.setAbbr1("br");
		t1.setScore1("1 (3)");
		t1.setPlayer2("Chile");
		t1.setAbbr2("cl");
		t1.setScore2("1 (2)");
		t1.setDescription("round of 16");
		t1.setStart("2014-06-28T13:00:00");

		Timeline t2 = new Timeline();
		t2.setPlayer1("Brazil");
		t2.setAbbr1("br");
		t2.setScore1("1 (3)");
		t2.setPlayer2("Chile");
		t2.setAbbr2("cl");
		t2.setScore2("1 (2)");
		t2.setDescription("round of 16");
		t2.setStart("2014-06-28T17:00:00");

		Timeline t3 = new Timeline();
		t3.setPlayer1("Brazil");
		t3.setAbbr1("br");
		t3.setScore1("1 (3)");
		t3.setPlayer2("Chile");
		t3.setAbbr2("cl");
		t3.setScore2("1 (2)");
		t3.setDescription("round of 16");
		t3.setStart("2014-06-29T13:00:00");

		Timeline t4 = new Timeline();
		t4.setPlayer1("Brazil");
		t4.setAbbr1("br");
		t4.setScore1("1 (3)");
		t4.setPlayer2("Chile");
		t4.setAbbr2("cl");
		t4.setScore2("1 (2)");
		t4.setDescription("round of 16");
		t4.setStart("2014-06-29T17:00:00");

		Timeline t5 = new Timeline();
		t5.setPlayer1("Brazil");
		t5.setAbbr1("br");
		t5.setScore1("1 (3)");
		t5.setPlayer2("Chile");
		t5.setAbbr2("cl");
		t5.setScore2("1 (2)");
		t5.setDescription("round of 16");
		t5.setStart("2014-06-30T13:00:00");

		Timeline t6 = new Timeline();
		t6.setPlayer1("Brazil");
		t6.setAbbr1("br");
		t6.setScore1("1 (3)");
		t6.setPlayer2("Chile");
		t6.setAbbr2("cl");
		t6.setScore2("1 (2)");
		t6.setDescription("round of 16");
		t6.setStart("2014-06-30T17:00:00");

		Timeline t7 = new Timeline();
		t7.setPlayer1("Brazil");
		t7.setAbbr1("br");
		t7.setScore1("1 (3)");
		t7.setPlayer2("Chile");
		t7.setAbbr2("cl");
		t7.setScore2("1 (2)");
		t7.setDescription("round of 16");
		t7.setStart("2014-07-01T13:00:00");

		Timeline t8 = new Timeline();
		t8.setPlayer1("Brazil");
		t8.setAbbr1("br");
		t8.setScore1("1 (3)");
		t8.setPlayer2("Chile");
		t8.setAbbr2("cl");
		t8.setScore2("1 (2)");
		t8.setDescription("round of 16");
		t8.setStart("2014-07-01T17:00:00");

		Timeline t9 = new Timeline();
		t9.setPlayer1("Brazil");
		t9.setAbbr1("br");
		t9.setScore1("1 (3)");
		t9.setPlayer2("Chile");
		t9.setAbbr2("cl");
		t9.setScore2("1 (2)");
		t9.setDescription("round of 16");
		t9.setStart("2014-07-04T13:00:00");

		Timeline t10 = new Timeline();
		t10.setPlayer1("Brazil");
		t10.setAbbr1("br");
		t10.setScore1("1 (3)");
		t10.setPlayer2("Chile");
		t10.setAbbr2("cl");
		t10.setScore2("1 (2)");
		t10.setDescription("round of 16");
		t10.setStart("2014-07-04T17:00:00");

		Timeline t11 = new Timeline();
		t11.setPlayer1("Brazil");
		t11.setAbbr1("br");
		t11.setScore1("1 (3)");
		t11.setPlayer2("Chile");
		t11.setAbbr2("cl");
		t11.setScore2("1 (2)");
		t11.setDescription("round of 16");
		t11.setStart("2014-07-01T13:00:00");

		Timeline t12 = new Timeline();
		t12.setPlayer1("Brazil");
		t12.setAbbr1("br");
		t12.setScore1("1 (3)");
		t12.setPlayer2("Chile");
		t12.setAbbr2("cl");
		t12.setScore2("1 (2)");
		t12.setDescription("round of 16");
		t12.setStart("2014-07-05T17:00:00");

		Timeline t13 = new Timeline();
		t13.setPlayer1("Brazil");
		t13.setAbbr1("br");
		t13.setScore1("1 (3)");
		t13.setPlayer2("Chile");
		t13.setAbbr2("cl");
		t13.setScore2("1 (2)");
		t13.setDescription("round of 16");
		t13.setStart("2014-07-08T17:00:00");

		Timeline t14 = new Timeline();
		t14.setPlayer1("Brazil");
		t14.setAbbr1("br");
		t14.setScore1("1 (3)");
		t14.setPlayer2("Chile");
		t14.setAbbr2("cl");
		t14.setScore2("1 (2)");
		t14.setDescription("round of 16");
		t14.setStart("2014-07-09T17:00:00");

		Timeline t15 = new Timeline();
		t15.setPlayer1("Brazil");
		t15.setAbbr1("br");
		t15.setScore1("1 (3)");
		t15.setPlayer2("Chile");
		t15.setAbbr2("cl");
		t15.setScore2("1 (2)");
		t15.setDescription("round of 16");
		t15.setStart("2014-07-13T16:00:00");

		List<Timeline> list = new ArrayList<>();
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		list.add(t6);
		list.add(t7);
		list.add(t8);
		list.add(t9);
		list.add(t10);
		list.add(t11);
		list.add(t12);
		list.add(t13);
		list.add(t14);
		list.add(t15);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);

		model.addAttribute("fixtureList", json);

		System.out.println("Here it is");
		return "index";
	}

	@RequestMapping(value = "/fixtureChange", method = RequestMethod.GET)
	public String fixtureChange(ModelMap model, HttpServletRequest request) throws JsonProcessingException {

		Timeline t1 = new Timeline();
		t1.setPlayer1("Brazil22222222");
		t1.setAbbr1("br");
		t1.setScore1("1 (3)");
		t1.setPlayer2("Chile");
		t1.setAbbr2("cl");
		t1.setScore2("1 (2)");
		t1.setDescription("round of 16");
		t1.setStart("2014-06-28T13:00:00");

		Timeline t2 = new Timeline();
		t2.setPlayer1("Brazil");
		t2.setAbbr1("br");
		t2.setScore1("1 (3)");
		t2.setPlayer2("Chile");
		t2.setAbbr2("cl");
		t2.setScore2("1 (2)");
		t2.setDescription("round of 16");
		t2.setStart("2014-06-28T17:00:00");

		Timeline t3 = new Timeline();
		t3.setPlayer1("Brazil");
		t3.setAbbr1("br");
		t3.setScore1("1 (3)");
		t3.setPlayer2("Chile");
		t3.setAbbr2("cl");
		t3.setScore2("1 (2)");
		t3.setDescription("round of 16");
		t3.setStart("2014-06-29T13:00:00");

		Timeline t4 = new Timeline();
		t4.setPlayer1("Brazil");
		t4.setAbbr1("br");
		t4.setScore1("1 (3)");
		t4.setPlayer2("Chile");
		t4.setAbbr2("cl");
		t4.setScore2("1 (2)");
		t4.setDescription("round of 16");
		t4.setStart("2014-06-29T17:00:00");

		Timeline t5 = new Timeline();
		t5.setPlayer1("Brazil");
		t5.setAbbr1("br");
		t5.setScore1("1 (3)");
		t5.setPlayer2("Chile");
		t5.setAbbr2("cl");
		t5.setScore2("1 (2)");
		t5.setDescription("round of 16");
		t5.setStart("2014-06-30T13:00:00");

		List<Timeline> list = new ArrayList<>();
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);

		model.addAttribute("fixtureList", json);

		System.out.println("Here it is");
		return "index";
	}

	
	
	@RequestMapping(value = "/topClubs")
	public @ResponseBody ModelAndView topClubs() {

		
		return new ModelAndView("top","message","done");
	}
	
	@RequestMapping(value = "/dbGeo", method = RequestMethod.GET)
	public @ResponseBody ModelAndView dbGeoMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DbGeoMap dbGeo = new DbGeoMap();
		dbGeo.geoMap();
		
		return new ModelAndView("dbSuccess","message","done");
	}
	
	
	
	
}