package com.uottawa.twittervisual.business;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.ServletException;

import static com.mongodb.client.model.Filters.*;

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
public class DbGeoMap {
	private static final long serialVersionUID = 1L;

	public void geoMap() throws ServletException, IOException {

		// Creating a Mongo client
		MongoClient mongo = new MongoClient("localhost", 27017);

		// Creating Credentials
		MongoCredential credential;
		credential = MongoCredential.createCredential("sampleUser", "local", "password".toCharArray());
		System.out.println("Checking connection");

		// Accessing the database
		MongoDatabase database = mongo.getDatabase("cdpTweets");

		// //Creating a collection
		// database.createCollection("sampleCollection");
		// System.out.println("Collection created successfully");

		// Retieving a collection
		MongoCollection<Document> collection = database.getCollection("tweets");
		System.out.println("Collection MyDB selected successfully");

		// Getting the iterable object
		// FindIterable<Document> iterDoc = collection.find();
		// int i = 1;

		// Geographical map
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;

		// JSONArray jsonArray2 = new JSONArray();
		// JSONArray jsonArraySentiment = new JSONArray();
		// JSONObject jsonObject2 = null;
		// JSONObject jsonObject3 = null;
		// JSONObject jsonObject4 = null;

		
		
		List<Document> employees = (List<Document>) collection.find(ne("place", null)).into(new ArrayList<Document>());
		
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

				if(sentiment!=null) {
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

		}

		for (Map.Entry<String, int[]> country : countrySentiment.entrySet()) {
			int savedSentiment[] = new int[3];
			savedSentiment = countrySentiment.get(country.getKey());

			jsonObject = new JSONObject();
			String countryCode = country.getKey();
			String[] values = countryCode.split("_");

			if (values.length != 0) {
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

		}

		System.out.println(jsonArray);

		System.out.println("Total number of tweets:" + i);
		System.out.println("Geo enabled tweets:" + x);
		System.out.println(jsonArray);
		// Getting the iterator
		// Iterator it = iterDoc.iterator();

		// while (it.hasNext()) {
		//
		// it.
		// //System.out.println(it.next());
		// i++;
		// }

		FileWriter fileWriter = new FileWriter(
				"C:\\Users\\ankur\\Documents\\GitHub\\twitterVisualization\\WebContent\\resources\\data\\data.json");
		// Writting the jsonObject into sample.json
		fileWriter.write(jsonArray.toString());
		fileWriter.close();
		System.out.println("JSON Object Successfully written to the file!!");

		// checking nested JSON
		// for( Map.Entry<String, int[]> country:countrySentiment.entrySet())
		// {
		// int savedSentiment[] = new int[3];
		// savedSentiment = countrySentiment.get(country.getKey());
		//
		// jsonObject2 = new JSONObject();
		// jsonObject3 = new JSONObject();
		// String countryCode= country.getKey();
		// String [] values = countryCode.split("_");
		//
		// jsonObject2.put("name", values[0]);
		//
		// JSONObject json5 = new JSONObject();
		// json5.put("name", "positive");
		// json5.put("size", savedSentiment[0]);
		//
		// //jsonArraySentiment.put(json5);
		//
		// jsonObject2.put("Children", json5);
		//
		// jsonArray2.put(jsonObject2);
		// }
		//
		// FileWriter fileWriter2 = new
		// FileWriter("C:\\Users\\ankur\\Documents\\GitHub\\twitterVisualization\\WebContent\\resources\\data\\Flare_data.json");
		// // Writting the jsonObject into sample.json
		// fileWriter2.write(jsonArray2.toString());
		// fileWriter2.close();
		// System.out.println("JSON Object Successfully written to the file!!");
		// System.out.println("new json is:"+jsonArray2);

	}

	public void TeamTweetCount() throws ServletException, IOException {

		System.out.println("Graph 2 started");
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoCredential credential;
		credential = MongoCredential.createCredential("sampleUser", "local", "password".toCharArray());
		System.out.println("Checking connection");

		MongoDatabase database = mongo.getDatabase("cdpTweets");
		MongoCollection<Document> collection = database.getCollection("tweets-2018-4-18");
		MongoCollection<Document> collection2 = database.getCollection("teams");
		System.out.println("Collection MyDB selected successfully");

		// Geographical map
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		Map<String, int[]> countrySentiment = new HashMap<String, int[]>();

		List<Document> tweets = (List<Document>) collection.find().into(new ArrayList<Document>());

		int i = 0;
		int n = 1;
		int positive = 0, negative = 0, neutral = 0;

		for (Document emp : tweets) {
			i++;

			String teamId = (String) emp.get("teamId");

			// sentiment
			String sentiment = emp.getString("mySentiment");

			if (teamId != null) {
				System.out.println("teamId = " + teamId);

				if (countrySentiment.containsKey(teamId)) {
					int savedSentiment[] = new int[3];
					savedSentiment = countrySentiment.get(teamId);

					if (sentiment.equalsIgnoreCase("positive"))
						savedSentiment[0] = savedSentiment[0] + 1;
					if (sentiment.equalsIgnoreCase("negative"))
						savedSentiment[1] = savedSentiment[1] + 1;
					if (sentiment.equalsIgnoreCase("neutral"))
						savedSentiment[2] = savedSentiment[2] + 1;

					countrySentiment.put(teamId.toString(), savedSentiment);

				}

				else {
					int totalSentiments[] = { 0, 0, 0 };

					if (sentiment.equalsIgnoreCase("positive"))
						totalSentiments[0] = 1;
					if (sentiment.equalsIgnoreCase("negative"))
						totalSentiments[1] = 1;
					if (sentiment.equalsIgnoreCase("neutral"))
						totalSentiments[2] = 1;

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

			System.out.println("Key:" + country.getKey() + "Value:" + savedSentiment);

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
				"C:\\Users\\ankur\\eclipse-workspace\\MyProj_TwitterVisualization\\WebContent\\resources\\data\\updated_flare.json");
		// Writting the jsonObject into sample.json
		fileWriter.write(json);
		fileWriter.close();
		System.out.println("JSON Object Successfully written to the file!!");
		
		
		System.out.println(json);
	}
}