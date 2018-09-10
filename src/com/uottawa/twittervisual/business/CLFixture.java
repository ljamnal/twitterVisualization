package com.uottawa.twittervisual.business;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.uottawa.twittervisual.model.ConnectionFactory;
import com.uottawa.twittervisual.model.FixtureDetail;

@Component
public class CLFixture {

	/**
	 * This method is called to fetch the match details from schedule schema
	 * @return: returns the match schedule and team details
	 */
	public Map<String, List<FixtureDetail>> getFixtures() {

		Map<String, List<FixtureDetail>> fixtureList = new HashMap<>();
		//Create lists for saving details according to the different stages of CL
		List<FixtureDetail> quarterFinal = new ArrayList<>();
		List<FixtureDetail> semiFinal = new ArrayList<>();
		List<FixtureDetail> finals = new ArrayList<>();

		MongoClient mongo = ConnectionFactory.CONNECTION.getClient();
		MongoDatabase database = mongo.getDatabase("cdpTweets");

		// Retrieving 'schedule' collection to get match related details
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
			
			//Retrieving homeTeam details
			MongoCollection<Document> collection2 = database.getCollection("teams");
			List<Document> teams = (List<Document>) collection2.find(eq("teamId", homeTeamId))
					.into(new ArrayList<Document>());
			String homeTeamName = null;
			String awayTeamName = null;
			for(Document team : teams) {
				homeTeamName = team.getString("teamName");
				break;
			}
			//Retrieving AwayTeam details
			MongoCollection<Document> collection3 = database.getCollection("teams");
			List<Document> teams2 = (List<Document>) collection3.find(eq("teamId", awayTeamId))
					.into(new ArrayList<Document>());
			
			for(Document team : teams2) {
				awayTeamName = team.getString("teamName");
				break;
			}
			//Set the fetched values in model class
			FixtureDetail fd = new FixtureDetail();
			fd.setAwayTeamId(awayTeamId);
			fd.setHomeTeamId(homeTeamId);
			fd.setMatchId(matchId);
			fd.setMatchTime(time);
			fd.setStadium(stadium);
			fd.setTeams(fixture);
			fd.setHomeTeamName(homeTeamName);
			fd.setAwayTeamName(awayTeamName);

			if ("quarterfinal".equals(round)) {
				quarterFinal.add(fd);
			} else if ("semifinal".equals(round)) {
				semiFinal.add(fd);
			} else if ("final".equals(round)) {
				finals.add(fd);
			}
		}
		//Insert the details according the the appropriate CL stage
		fixtureList.put("semifinal", semiFinal);
		fixtureList.put("quarterfinal", quarterFinal);
		fixtureList.put("final", finals);

		//ConnectionFactory.CONNECTION.close();
		//mongo.close();
		return fixtureList;
	}
}