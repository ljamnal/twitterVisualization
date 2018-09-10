package com.uottawa.twittervisual.business;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.ServletException;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.uottawa.twittervisual.model.ConnectionFactory;
import com.uottawa.twittervisual.model.Tweets;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class DbGeoMap {
	private static final long serialVersionUID = 1L;

	/**
	 * This method is called on homepage to display the geography wise sentiment breakdown for Champions League
	 * @throws ServletException
	 * @throws IOException
	 */
	public void geoMap() throws ServletException, IOException {
		// Creating a Mongo client
		MongoClient mongo = ConnectionFactory.CONNECTION.getClient();
		// Accessing the database
		DB db = mongo.getDB("cdpTweets");
		// Retieving a collection
		DBCollection collection = db.getCollection("tweets");
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		BasicDBObject searchObject = new BasicDBObject();
		searchObject.put("place", new BasicDBObject("$ne", null));
		BasicDBObject projectionObject = new BasicDBObject();
		projectionObject.put("place", 1);
		projectionObject.put("sentiment_tweets", 1);
		//Saves the data in the db cursor
		System.out.println("get query");
		System.out.println(new Date());
		DBCursor cursor = collection.find(searchObject, projectionObject).batchSize(10000);
		System.out.println("get query completed");
		System.out.println(new Date());
		Map<String, int[]> countrySentiment = new HashMap<String, int[]>();

		int i=0;
		int j=0;
		while (cursor.hasNext()) {
			System.out.println("j" + (++j));
			DBObject tweet = cursor.next();
			DBObject location = (DBObject) tweet.get("place");
			//check for geo enabled tweets
			if (location != null) {
				System.out.println("i:" + (++i));
				//check if the country already exists in the map, if yes, increment the sentiment value
				if (countrySentiment
						.containsKey((String)(location.get("country_code")))){
					int savedSentiment[] = countrySentiment
							.get((String)(location.get("country_code")));

					String sentiment = (String) tweet.get("sentiment_tweets");
					if(sentiment != null) {
						if ("positive".equals(sentiment)) {
							savedSentiment[0]++;
						} else if ("negative".equals(sentiment)) {
							savedSentiment[1]++;
						} else if ("neutral".equals(sentiment)) {
							savedSentiment[2]++;
						}
						countrySentiment.put(((String)(location.get("country_code"))),
								savedSentiment);
					}


					
				} 
				//if the country doesn't exist in the map, create the entry and increment sentiment values
				else {
					int totalSentiments[] = { 0, 0, 0 };
					
					String sentiment = (String) tweet.get("sentiment_tweets");
					if(sentiment != null) {
						if ("positive".equals(sentiment)) {
							totalSentiments[0] = 1;
						} else if ("negative".equals(sentiment)) {
							totalSentiments[1] = 1;
						} else if ("neutral".equals(sentiment)) {
							totalSentiments[2] = 1;
						}
						countrySentiment.put(((String)(location.get("country_code"))),
								totalSentiments);
					}
					
					
				}
				}
		}

		for (Map.Entry<String, int[]> country : countrySentiment.entrySet()) {
			int savedSentiment[] = new int[3];
			savedSentiment = countrySentiment.get(country.getKey());
			jsonObject = new JSONObject();
			String countryCode = country.getKey();
			String[] values = countryCode.split("_");
			
			//set the value in a json object
			if (values.length != 0) {
//				jsonObject.put("name", values[0]);

				jsonObject.put("id", countryCode);
				// positive
				jsonObject.put("area", savedSentiment[0]);
				// negative
				jsonObject.put("density", savedSentiment[1]);
				// neutral
				jsonObject.put("population", savedSentiment[2]);
				
				if(savedSentiment[0]>savedSentiment[1] && savedSentiment[0]>savedSentiment[2])
					jsonObject.put("color",1);
				
				else if(savedSentiment[1]>savedSentiment[0] && savedSentiment[1]>savedSentiment[2])
					jsonObject.put("color",11);
				
				else 
					jsonObject.put("color",21);

				jsonArray.put(jsonObject);
			}
		}
		
		//write the jsonArray to a file
		FileWriter fileWriter = new FileWriter(
				"C:\\Users\\Samah\\Desktop\\twitterVisualization\\WebContent\\resources\\data\\data.json");
		// Writting the jsonObject into sample.json
		fileWriter.write(jsonArray.toString());
		fileWriter.close();
		//mongo.close();
		//ConnectionFactory.CONNECTION.close();
}

	public void TeamTweetCount() throws ServletException, IOException {

		MongoClient mongo = ConnectionFactory.CONNECTION.getClient();
		MongoCredential credential;
		credential = MongoCredential.createCredential("sampleUser", "local", "password".toCharArray());

		MongoDatabase database = mongo.getDatabase("cdpTweets");
		MongoCollection<Document> collection = database.getCollection("tweets-2018-4-18");
		MongoCollection<Document> collection2 = database.getCollection("teams");

		// Geographical map
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		Map<String, int[]> countrySentiment = new HashMap<String, int[]>();

		List<Document> tweets = (List<Document>) collection.find().into(new ArrayList<Document>());

		for (Document emp : tweets) {

			String teamId = (String) emp.get("teamId");
			// sentiment
			String sentiment = emp.getString("mySentiment");
			if (teamId != null) {
				
				if (countrySentiment.containsKey(teamId)) {
					int savedSentiment[] = new int[3];
					savedSentiment = countrySentiment.get(teamId);

					Random rand = new Random();
					int value = rand.nextInt(3);
					savedSentiment[value] = savedSentiment[value] + 1;
					
					/*if (sentiment.equalsIgnoreCase("positive"))
						savedSentiment[0] = savedSentiment[0] + 1;
					if (sentiment.equalsIgnoreCase("negative"))
						savedSentiment[1] = savedSentiment[1] + 1;
					if (sentiment.equalsIgnoreCase("neutral"))
						savedSentiment[2] = savedSentiment[2] + 1;*/

					countrySentiment.put(teamId.toString(), savedSentiment);

				}

				else {
					int totalSentiments[] = { 0, 0, 0 };

					Random rand = new Random();
					int value = rand.nextInt(3);
					totalSentiments[value] = 1;
					
					/*if (sentiment.equalsIgnoreCase("positive"))
						totalSentiments[0] = 1;
					if (sentiment.equalsIgnoreCase("negative"))
						totalSentiments[1] = 1;
					if (sentiment.equalsIgnoreCase("neutral"))
						totalSentiments[2] = 1;*/

					countrySentiment.put(teamId.toString(), totalSentiments);
				}

			}

		}

		Tweets root = new Tweets();
		root.setName("Total tweets count");

		List<Tweets> rootChildren = new ArrayList<>();

		
		Map<String, int[]> treeMap = new TreeMap<String, int[]>(countrySentiment);
		
		for (Map.Entry<String, int[]> country : treeMap.entrySet()) {

			int teamId = Integer.parseInt(country.getKey());
			int savedSentiment[] = new int[3];
			savedSentiment = countrySentiment.get(country.getKey());

			List<Document> teams = (List<Document>) collection2.find(eq("teamId", teamId))
					.into(new ArrayList<Document>());

			String teamName = null;
			for (Document teamList : teams) {

				teamName = teamList.getString("teamName");

			}

			Tweets item = new Tweets();
			item.setName(teamName);
			List<Tweets> itemChildren = new ArrayList<>();
			
			jsonObject = new JSONObject();

			Tweets child1 = new Tweets();
			child1.setName("positive");
			child1.setCount(savedSentiment[0]);

			Tweets child2 = new Tweets();
			child2.setName("negative");
			child2.setCount(savedSentiment[1]);

			Tweets child3 = new Tweets();
			child3.setName("neutral");
			child3.setCount(savedSentiment[2]);

			itemChildren.add(child1);
			itemChildren.add(child2);
			itemChildren.add(child3);

			item.setChildren(itemChildren);
			rootChildren.add(item);

			jsonObject.put("name", teamName);
			// positive
			jsonObject.put("positive", savedSentiment[0]);
			// negative
			jsonObject.put("negative", savedSentiment[1]);
			// neutral
			jsonObject.put("neutral", savedSentiment[2]);

			jsonArray.put(jsonObject);
		}
		root.setChildren(rootChildren);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(root);

		FileWriter fileWriter = new FileWriter(
				"C:\\Users\\Samah\\Desktop\\twitterVisualization\\WebContent\\resources\\data\\updated_flare.json");
		// Writting the jsonObject into json file
		fileWriter.write(json);
		fileWriter.close();
		//ConnectionFactory.CONNECTION.close();
		//mongo.close();
	}
}