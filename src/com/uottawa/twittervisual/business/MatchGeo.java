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
import java.util.Random;

import javax.servlet.ServletException;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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

import java.io.FileWriter;
import java.util.Arrays;
import com.mongodb.MongoClient;
import com.uottawa.twittervisual.model.ConnectionFactory;

@Component
public class MatchGeo {
	private static final long serialVersionUID = 1L;

	/**
	 * This method is called to fetch match wise tweet data to render it based on geography
	 * @param matchId
	 * @param homeTeamId
	 * @param awayTeamId
	 * @param homeTeamName
	 * @param awayTeamName
	 * @throws IOException
	 */
	public void getMatchWiseTweetDetails(int matchId, int homeTeamId, int awayTeamId, String homeTeamName,
			String awayTeamName) throws IOException {

		JSONObject jsonObject = null;
		JSONArray jsonArray = new JSONArray();
		MongoClient mongo = ConnectionFactory.CONNECTION.getClient();
		DB db = mongo.getDB("cdpTweets");

		// Retrieving a collection
		DBCollection collection = db.getCollection("schedule");

		String databaseName = "";
		BasicDBObject query = new BasicDBObject();
		query.put("matchId", matchId);

		DBCursor scheduleCursor = collection.find(query);
		Date matchDate = null;

		while (scheduleCursor.hasNext()) {

			DBObject schedule = scheduleCursor.next();
			matchDate = (Date) schedule.get("matchTime");
			;

			Calendar cal = Calendar.getInstance();
			cal.setTime(matchDate);

			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);

			databaseName = "tweets-" + year + "-" + month + "-" + day;
			break;
		}

		DBCollection collection2 = db.getCollection(databaseName);
		List<BasicDBObject> searchArguments = new ArrayList<BasicDBObject>();
		BasicDBObject searchObject = new BasicDBObject();
		searchArguments.add(new BasicDBObject("place", new BasicDBObject("$ne", null)));
		searchArguments.add(new BasicDBObject("matchId", "" + matchId));
		searchObject.put("$and", searchArguments);

		BasicDBObject projectionObject = new BasicDBObject();
		projectionObject.put("teamId", 1);
		projectionObject.put("place", 1);
		projectionObject.put("sentiment_tweets", 1);
		
		DBCursor cursor = collection2.find(searchObject, projectionObject).batchSize(10000);
		Map<String, int[]> countrySentiment = new HashMap<String, int[]>();
		while (cursor.hasNext()) {

			DBObject tweet = cursor.next();
			DBObject location = (DBObject) tweet.get("place");

			if (location != null) {

				String country = (String) location.get("country");
				String country_code = (String) location.get("country_code");

				if (countrySentiment.containsKey(country + "_" + country_code)) {

					int savedSentiment[] = new int[9];

					savedSentiment = countrySentiment.get(country + "_" + country_code);

					if (Integer.toString(homeTeamId).equals((String)(tweet.get("teamId")))) {
						
						String sentiment = (String) tweet.get("sentiment_tweets");
						if(sentiment != null) {
							if ("positive".equals(sentiment)) {
								savedSentiment[0]++;
							} else if ("negative".equals(sentiment)) {
								savedSentiment[1]++;
							} else if ("neutral".equals(sentiment)) {
								savedSentiment[2]++;
							}
						}
						
					}

					else if (Integer.toString(awayTeamId).equals((String)(tweet.get("teamId")))) {
						
						String sentiment = (String) tweet.get("sentiment_tweets");
						if(sentiment != null) {
							if ("positive".equals(sentiment)) {
								savedSentiment[4]++;
							} else if ("negative".equals(sentiment)) {
								savedSentiment[5]++;
							} else if ("neutral".equals(sentiment)) {
								savedSentiment[6]++;
							}
						}
	

					}
					countrySentiment.put(country + "_" + country_code, savedSentiment);

				}

				else {
					int savedSentiment[] = new int[9];
					if (Integer.toString(homeTeamId).equals((String)(tweet.get("teamId")))) {
						
						String sentiment = (String) tweet.get("sentiment_tweets");
						if(sentiment != null) {
							if ("positive".equals(sentiment)) {
								savedSentiment[0]++;
							} else if ("negative".equals(sentiment)) {
								savedSentiment[1]++;
							} else if ("neutral".equals(sentiment)) {
								savedSentiment[2]++;
							}
						}
					} else if (Integer.toString(awayTeamId).equals((String)(tweet.get("teamId")))) {
						String sentiment = (String) tweet.get("sentiment_tweets");
						if(sentiment != null) {
							if ("positive".equals(sentiment)) {
								savedSentiment[4]++;
							} else if ("negative".equals(sentiment)) {
								savedSentiment[5]++;
							} else if ("neutral".equals(sentiment)) {
								savedSentiment[6]++;
							}
						}
					}
					countrySentiment.put(country + "_" + country_code, savedSentiment);

				}

			}
		}
		// json

		for (Map.Entry<String, int[]> country2 : countrySentiment.entrySet()) {
			int savedSentiment[] = new int[9];
			savedSentiment = countrySentiment.get(country2.getKey());

			savedSentiment[3] = savedSentiment[0] + savedSentiment[1] + savedSentiment[2];
			savedSentiment[7] = savedSentiment[4] + savedSentiment[5] + savedSentiment[6];

			if (!(savedSentiment[3] == 0 && savedSentiment[7] == 0)) {

				jsonObject = new JSONObject();
				String countryCode = country2.getKey();

				String[] values = countryCode.split("_");
				if (values.length != 0) {

					if (savedSentiment[3] > savedSentiment[7]) {
						savedSentiment[8] = 0;
					} else {
						savedSentiment[8] = 1;
					}

					jsonObject.put("name", values[0]);
					jsonObject.put("id", values[1]);

					jsonObject.put("homePositiveCount", savedSentiment[0]);
					jsonObject.put("homeNegativeCount", savedSentiment[1]);
					jsonObject.put("homeNeutralCount", savedSentiment[2]);
					jsonObject.put("homeTotalCount", savedSentiment[3]);
					jsonObject.put("awayPositiveCount", savedSentiment[4]);
					jsonObject.put("awayNegativeCount", savedSentiment[5]);
					jsonObject.put("awayNeutralCount", savedSentiment[6]);
					jsonObject.put("awayTotalCount", savedSentiment[4] + savedSentiment[5] + savedSentiment[6]);
					jsonObject.put("color", savedSentiment[8]);

					jsonArray.put(jsonObject);
				}

			}
		}

		FileWriter fileWriter = new FileWriter(
				
				"C:\\Users\\Samah\\Desktop\\twitterVisualization\\WebContent\\resources\\data\\UpdatedGeodata_"
						+ matchId + ".json");

		// Writting the jsonObject into sample.json
		fileWriter.write(jsonArray.toString());
		fileWriter.close();
		
		//writing to csv File for Stacked Bar Graph
		//writing to file
		       String csvFile = "C:\\Users\\Samah\\Desktop\\twitterVisualization\\WebContent\\resources\\data\\TweetsSummaryMatch.csv";
        
		        FileWriter writer = new FileWriter(csvFile);

				// Delimiter used in CSV file
						final String COMMA_DELIMITER = ",";
						final String NEW_LINE_SEPARATOR = "\n";
						// CSV file header

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
		        //ConnectionFactory.CONNECTION.close();
		       // mongo.close();
		    }

}