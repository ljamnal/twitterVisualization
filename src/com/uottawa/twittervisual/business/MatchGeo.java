package com.uottawa.twittervisual.business;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import java.io.FileWriter;
import java.util.Arrays;

@Component
public class MatchGeo {
	private static final long serialVersionUID = 1L;

	

	public void getMatchWiseTweetDetails(int matchId, int homeTeamId, int awayTeamId, String homeTeamName,
			String awayTeamName) throws IOException {

		JSONObject jsonObject = null;
		JSONArray jsonArray = new JSONArray();
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase database = mongo.getDatabase("cdpTweets");

		// Retrieving a collection
		MongoCollection<Document> collection = database.getCollection("schedule");

		String databaseName = "";
		List<Document> employees = (List<Document>) collection.find(eq("matchId", matchId))
				.into(new ArrayList<Document>());

		// Cursor cursor = collection.find(query);
		Date matchDate = null;

		for (Document emp : employees) {

			matchDate = (Date) emp.get("matchTime");
			;
			System.out.println(matchDate.getTime());

			Calendar cal = Calendar.getInstance();
			cal.setTime(matchDate);

			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);

			System.out.println(year);
			System.out.println(month);
			System.out.println(day);

			databaseName = "tweets-" + year + "-" + month + "-" + day;

			System.out.println("tweets-" + year + "-" + month + "-" + day);

		}

		// int homePositiveCount = 0; //array element1
		// int homeNegativeCount = 0;//array element2
		// int homeNeutralCount = 0;//array element3
		// int homeTotalCount = 0;//array element4
		// int awayPositiveCount = 0;//array element5
		// int awayNegativeCount = 0;//array element6
		// int awayNeutralCount = 0;//array element7
		// int awayTotalCount = 0;//array element8
		// int color = 0;//array element9

		MongoCollection<Document> collection2 = database.getCollection("tweets-2018-4-18");
		List<Document> filteredTweets = (List<Document>) collection2.find(eq("matchId", Integer.toString(matchId)))
				.into(new ArrayList<Document>());

		Map<String, int[]> countrySentiment = new HashMap<String, int[]>();

		for (Document tweet : filteredTweets) {

			Document location = (Document) tweet.get("place");

			if (location != null) {

				String country = location.getString("country");
				String country_code = location.getString("country_code");

				if (countrySentiment.containsKey(country + "_" + country_code)) {

					int savedSentiment[] = new int[9];

					savedSentiment = countrySentiment.get(country + "_" + country_code);

					if (tweet.getString("teamId").equals(Integer.toString(homeTeamId))) {

						if ("positive".equals(tweet.getString("mySentiment"))) {
							savedSentiment[0]++;
						} else if ("negative".equals(tweet.getString("mySentiment"))) {
							savedSentiment[1]++;
						} else if ("neutral".equals(tweet.getString("mySentiment"))) {
							savedSentiment[2]++;
						}
						savedSentiment[3] = savedSentiment[0] + savedSentiment[1] + savedSentiment[2];
					}

					else if (tweet.getString("teamId").equals(Integer.toString(awayTeamId))) {
						if ("positive".equals(tweet.getString("mySentiment"))) {
							savedSentiment[4]++;
						} else if ("negative".equals(tweet.getString("mySentiment"))) {
							savedSentiment[5]++;
						} else if ("neutral".equals(tweet.getString("mySentiment"))) {
							savedSentiment[6]++;
						}
						savedSentiment[7] = savedSentiment[4] + savedSentiment[5] + savedSentiment[6];
						if (savedSentiment[3] > savedSentiment[7]) {
							savedSentiment[8] = 0;
						} else {
							savedSentiment[8] = 1;
						}

					}
					countrySentiment.put(country + "_" + country_code, savedSentiment);

				}

				else {
					int savedSentiment[] = new int[9];
					if (tweet.getString("teamId").equals(Integer.toString(homeTeamId))) {

						if ("positive".equals(tweet.getString("mySentiment"))) {
							savedSentiment[0]++;
						} else if ("negative".equals(tweet.getString("mySentiment"))) {
							savedSentiment[1]++;
						} else if ("neutral".equals(tweet.getString("mySentiment"))) {
							savedSentiment[2]++;
						}
						savedSentiment[3] = savedSentiment[0] + savedSentiment[1] + savedSentiment[2];
					} else if (tweet.getString("teamId").equals(Integer.toString(awayTeamId))) {
						if ("positive".equals(tweet.getString("mySentiment"))) {
							savedSentiment[4]++;
						} else if ("negative".equals(tweet.getString("mySentiment"))) {
							savedSentiment[5]++;
						} else if ("neutral".equals(tweet.getString("mySentiment"))) {
							savedSentiment[6]++;
						}
						savedSentiment[7] = savedSentiment[4] + savedSentiment[5] + savedSentiment[6];
						if (savedSentiment[3] > savedSentiment[7]) {
							savedSentiment[8] = 0;
						} else {
							savedSentiment[8] = 1;
						}

					}
					countrySentiment.put(country + "_" + country_code, savedSentiment);

				}

			}
		}
		// json

		System.out.println("Complete map is" + countrySentiment);
		for (Map.Entry<String, int[]> country2 : countrySentiment.entrySet()) {
			int savedSentiment[] = new int[9];
			savedSentiment = countrySentiment.get(country2.getKey());

			if (!(savedSentiment[3] == 0 && savedSentiment[7] == 0)) {

				jsonObject = new JSONObject();
				String countryCode = country2.getKey();

				String[] values = countryCode.split("_");
				jsonObject.put("name", values[0]);
				jsonObject.put("id", values[1]);

				jsonObject.put("homePositiveCount", savedSentiment[0]);
				jsonObject.put("homeNegativeCount", savedSentiment[1]);
				jsonObject.put("homeNeutralCount", savedSentiment[2]);
				jsonObject.put("homeTotalCount", savedSentiment[3]);
				jsonObject.put("awayPositiveCount", savedSentiment[4]);
				jsonObject.put("awayNegativeCount", savedSentiment[5]);
				jsonObject.put("awayNeutralCount", savedSentiment[6]);
				jsonObject.put("awayTotalCount", savedSentiment[7]);
				jsonObject.put("color", savedSentiment[8]);

				jsonArray.put(jsonObject);
			}
		}

		System.out.println("Updated json is" + jsonArray);

		FileWriter fileWriter = new FileWriter(
				
				"C:\\Users\\ankur\\eclipse-workspace\\MyProj_TwitterVisualization\\WebContent\\resources\\data\\UpdatedGeodata_"
						+ matchId + ".json");

		// Writting the jsonObject into sample.json
		fileWriter.write(jsonArray.toString());
		fileWriter.close();
		System.out.println("JSON Object Successfully written to the file!!");
		


		//writing to csv File for Stacked Bar Graph
		//writing to file
		       String csvFile = "C:\\Users\\ankur\\eclipse-workspace\\MyProj_TwitterVisualization\\WebContent\\resources\\data\\TweetsSummaryMatch.csv";
        
		        FileWriter writer = new FileWriter(csvFile);

				// Delimiter used in CSV file
						final String COMMA_DELIMITER = ",";
						final String NEW_LINE_SEPARATOR = "\n";
						// CSV file header
						
						//final String FILE_HEADER = "State,Team 1 ,Team2";
						//writer.append(FILE_HEADER.toString());
						writer.append("State,"+homeTeamName+","+awayTeamName);
						
				// Add a new line separator after the header
						writer.append(NEW_LINE_SEPARATOR);
						
				// Write a new object list to the CSV file
						
						int totalHomePositive = 0;
						int totalAwayPositive = 0;
						int totalHomeNegative = 0;
						int totalAwayNegative = 0;
						int totalHomeNeutral = 0;
						int totalAwayNeutral = 0;
						
						for (int i = 0; i < jsonArray.length(); i++) { 
							
						jsonObject=jsonArray.getJSONObject(i);
						totalHomePositive = totalHomePositive + (int) jsonObject.get("homePositiveCount");
						totalHomeNegative = totalHomeNegative + (int) jsonObject.get("homeNegativeCount");
						totalHomeNeutral = totalHomeNeutral + (int) jsonObject.get("homeNeutralCount");
						totalAwayPositive = totalAwayPositive + (int) jsonObject.get("awayPositiveCount");
						totalAwayNegative = totalAwayNegative + (int) jsonObject.get("awayNegativeCount");
						totalAwayNeutral = totalAwayNeutral + (int) jsonObject.get("awayNeutralCount");
						}
								
					writer.append("Positive");
					writer.append(COMMA_DELIMITER);
					writer.append(Integer.toString(totalHomePositive));
					writer.append(COMMA_DELIMITER);
					writer.append(Integer.toString(totalAwayPositive));
					writer.append(NEW_LINE_SEPARATOR);
					writer.append("Negative");
					writer.append(COMMA_DELIMITER);
					writer.append(Integer.toString(totalHomeNegative));
					writer.append(COMMA_DELIMITER);
					writer.append(Integer.toString(totalAwayNegative));
					writer.append(NEW_LINE_SEPARATOR);
					writer.append("Neutral");
					writer.append(COMMA_DELIMITER);
					writer.append(Integer.toString(totalHomeNeutral));
					writer.append(COMMA_DELIMITER);
					writer.append(Integer.toString(totalAwayNeutral));
					writer.append(NEW_LINE_SEPARATOR);
				
		        
		        writer.flush();
		        writer.close();

		    }

}