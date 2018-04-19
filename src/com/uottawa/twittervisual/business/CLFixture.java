package com.uottawa.twittervisual.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.uottawa.twittervisual.model.FixtureDetail;

@Component
public class CLFixture {

	public Map<String, List<FixtureDetail>> getFixtures() {

		Map<String, List<FixtureDetail>> fixtureList = new HashMap<>();

		List<FixtureDetail> quarterFinal = new ArrayList<>();
		List<FixtureDetail> semiFinal = new ArrayList<>();
		List<FixtureDetail> finals = new ArrayList<>();
		
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase database = mongo.getDatabase("cdpTweets");

		// Retrieving a collection
		MongoCollection<Document> collection = database.getCollection("schedule");
		List<Document> employees = (List<Document>) collection.find().into(new ArrayList<Document>());

		for (Document emp : employees) {
			String round = (String) emp.get("round");
			String stadium = (String) emp.get("stadium");
			String time = (String) emp.get("timeForGUI");
			String fixture = (String) emp.get("fixture");
			int matchId = emp.getInteger("matchId");
			int homeTeamId = emp.getInteger("homeTeamId");
			int awayTeamId = emp.getInteger("awayTeamId");
			
			
			FixtureDetail fd = new FixtureDetail();
			fd.setAwayTeamId(awayTeamId);
			fd.setHomeTeamId(homeTeamId);
			fd.setMatchId(matchId);
			fd.setMatchTime(time);
			fd.setStadium(stadium);
			fd.setTeams(fixture);
			
			if("quarterfinal".equals(round)) {
				quarterFinal.add(fd);
			} else if("semifinal".equals(round)) {
				semiFinal.add(fd);
			} else if("final".equals(round)) {
				finals.add(fd);
			}
		}
		
		fixtureList.put("semifinal", semiFinal);
		fixtureList.put("quarterfinal", quarterFinal);
		fixtureList.put("final", finals);
		
		mongo.close();
		return fixtureList;
	}
}