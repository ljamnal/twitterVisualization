package com.uottawa.twittervisual.business;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.uottawa.twittervisual.model.Tweets;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MatchGeo {
	private static final long serialVersionUID = 1L;

	public void geoMatchData(int matchId) throws ServletException, IOException {

		String matchIdString = Integer.toString(matchId);
		System.out.println("Match id is:"+matchIdString);
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoCredential credential;
		credential = MongoCredential.createCredential("sampleUser", "local", "password".toCharArray());
		System.out.println("Checking connection");
		MongoDatabase database = mongo.getDatabase("cdpTweets");
		MongoCollection<Document> collection = database.getCollection("tweets-2018-4-18");
		System.out.println("Collection MyDB selected successfully");
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		List<Document> employees = (List<Document>) collection.find(eq("matchId", matchIdString)).into(new ArrayList<Document>());

		int i = 0;
		int x = 0;
		int n = 1;
		int positive = 0, negative = 0, neutral = 0;

		Map<String, int[]> countrySentiment = new HashMap<String, int[]>();

		for (Document emp : employees) {
			i++;

			Document location = (Document) emp.get("place");

			// sentiment
			String sentiment = emp.getString("mySentiment");

			if (location != null) {

				System.out.println("country = " + location.getString("country"));
				System.out.println("country code = " + location.getString("country_code"));

				x++;

				if (countrySentiment
						.containsKey(location.getString("country") + "_" + location.getString("country_code"))) {
					int savedSentiment[] = new int[3];
					savedSentiment = countrySentiment
							.get(location.getString("country") + "_" + location.getString("country_code"));

					if (sentiment.equalsIgnoreCase("positive"))
						savedSentiment[0] = savedSentiment[0] + 1;
					if (sentiment.equalsIgnoreCase("negative"))
						savedSentiment[1] = savedSentiment[1] + 1;
					if (sentiment.equalsIgnoreCase("neutral"))
						savedSentiment[2] = savedSentiment[2] + 1;

					countrySentiment.put(location.getString("country") + "_" + location.getString("country_code"),
							savedSentiment);

				} else {
					int totalSentiments[] = { 0, 0, 0 };

					if (sentiment.equalsIgnoreCase("positive"))
						totalSentiments[0] = 1;
					if (sentiment.equalsIgnoreCase("negative"))
						totalSentiments[1] = 1;
					if (sentiment.equalsIgnoreCase("neutral"))
						totalSentiments[2] = 1;

					countrySentiment.put(location.getString("country") + "_" + location.getString("country_code"),
							totalSentiments);
				}
			}
		}

		for (Map.Entry<String, int[]> country : countrySentiment.entrySet()) {
			int savedSentiment[] = new int[3];
			savedSentiment = countrySentiment.get(country.getKey());

			jsonObject = new JSONObject();
			String countryCode = country.getKey();
			String[] values = countryCode.split("_");

			jsonObject.put("name", values[0]);
			jsonObject.put("id", values[1]);
			// positive
			jsonObject.put("area", savedSentiment[0]);
			// negative
			jsonObject.put("density", savedSentiment[1]);
			// neutral
			jsonObject.put("population", savedSentiment[2]);

			jsonArray.put(jsonObject);
		}

		System.out.println(jsonArray);

		System.out.println("Total number of tweets:" + i);
		System.out.println("Geo enabled tweets:" + x);
		System.out.println(jsonArray);
		FileWriter fileWriter = new FileWriter(
				"C:\\Users\\ankur\\Documents\\GitHub\\twitterVisualization\\WebContent\\resources\\data\\Geodata_" + matchId +".json");
		
		// Writting the jsonObject into sample.json
		fileWriter.write(jsonArray.toString());
		fileWriter.close();
		System.out.println("JSON Object Successfully written to the file!!");

	}
}