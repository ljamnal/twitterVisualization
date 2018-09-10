package com.uottawa.twittervisual.business;

import static com.mongodb.client.model.Filters.eq;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.operation.OrderBy;
import com.uottawa.twittervisual.model.ConnectionFactory;
import com.uottawa.twittervisual.model.DataPointsModel;
import com.uottawa.twittervisual.model.Sentiment;

@Component
public class LiveMatch {

	/**
	 * This method is called to fetch tweet and match details whenever the user clicks on a match.
	 * @param matchId: id of the match clicked on
	 * @param homeTeamId: Home team of the match clicked on
	 * @param awayTeamId: away team of the match clicked on
	 * @return
	 */
	public Sentiment getMatchDetails(int matchId, int homeTeamId, int awayTeamId) {

		MongoClient mongoClient = ConnectionFactory.CONNECTION.getClient();
		DB db = mongoClient.getDB("cdpTweets");
		// Retrieving a collection
		DBCollection scheduleCollection = db.getCollection("schedule");
		String databaseName = "";

		Sentiment sentiment = null;
		//Creating a query object
		BasicDBObject query = new BasicDBObject();
		query.put("matchId", matchId);

		DBCursor scheduleCursor = scheduleCollection.find(query);
		Date matchDate = null;
		String fixture = null;
		while (scheduleCursor.hasNext()) {

			DBObject schedule = scheduleCursor.next();
			matchDate = (Date) schedule.get("matchTime");
			fixture = (String) schedule.get("fixture");
			Calendar cal = Calendar.getInstance();
			cal.setTime(matchDate);

			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			databaseName = "tweets-" + year + "-" + month + "-" + day;
			break;
		}

		int max = 0;
		long currentTime = new Date().getTime();
		int a = 1;
		if (currentTime > matchDate.getTime()) {
			DBCollection coll = db.getCollection(databaseName);
			long matchEndTime = matchDate.getTime() + 1000 * 60 * 100;
			long maximum = 0l;
			if (matchEndTime > currentTime) {
				maximum = currentTime;
			} else {
				maximum = matchEndTime;
			}

			List<BasicDBObject> searchArguments = new ArrayList<BasicDBObject>();
			BasicDBObject searchObject = new BasicDBObject();
			searchArguments.add(
					new BasicDBObject("timestamp_ms", new BasicDBObject("$gt", Long.toString(matchDate.getTime()))));
			searchArguments.add(new BasicDBObject("timestamp_ms", new BasicDBObject("$lt", Long.toString(maximum))));
			searchArguments.add(new BasicDBObject("matchId", "" + matchId));
			searchObject.put("$and", searchArguments);

			BasicDBObject projectionObject = new BasicDBObject();
			projectionObject.put("teamId", 1);
			projectionObject.put("timestamp_ms", 1);
			projectionObject.put("sentiment_tweets", 1);
			
			DBCursor cursor = coll.find(searchObject, projectionObject).batchSize(10000);
			String fileName = "C:\\test\\Match" + matchId + ".csv";
			File f = new File(fileName);
			if (f.exists() && !f.isDirectory() && a == 0) {
				float maxTime = getMaximumTime(fileName);
				long minimumTime = (long) (matchDate.getTime() + 1000 * 60 * maxTime + 1000 * 30);
				sentiment = countSentiment(maximum, minimumTime, matchId, matchDate, cursor, homeTeamId, awayTeamId);
			} else {

				long minimumTime = (long) (matchDate.getTime() + 1000 * 30);
				sentiment = countSentiment(maximum, matchDate.getTime(), matchId, matchDate, cursor, homeTeamId, awayTeamId);
			}
			cursor.close();
		}
		scheduleCursor.close();
		//mongoClient.close();
		return sentiment;
	}
	
	/**
	 * This method is called to calculate the count of tweets during real-time visual implementation
	 * @param currentTime: the current time
	 * @param matchTime: the time the match is scheduled
	 * @param matchId: id of the match
	 * @param matchDate: date of the match
	 * @param cursor
	 * @param homeTeamId: id of the home team
	 * @param awayTeamId: id of the away team
	 * @return
	 */
	private Sentiment countSentiment(long currentTime, long matchTime, int matchId, Date matchDate, DBCursor cursor,
			int homeTeamId, int awayTeamId) {

		String content = "";
		String content2 = "";

		//Map to store the sentiment values
		Map<Integer, Sentiment> sentiments = new HashMap<>();
		Map<Integer, Sentiment> averageSentiments = new HashMap<>();
		
		List<Integer> listCount = new ArrayList<>();
		List<Integer> listCount2 = new ArrayList<>();
		
		while (cursor.hasNext()) {
			DBObject current = cursor.next();
			long tweetTimestamp = Long.parseLong((String) current.get("timestamp_ms"));
			int pos = (int) (Math.ceil((tweetTimestamp - matchTime) / 30000));

			if (("" + homeTeamId).equals((String) (current.get("teamId")))) {
				
				String sentiment = (String) current.get("sentiment_tweets");
				//fetch sentiments
				
				if(sentiment != null) {
					if (sentiments.get(pos) == null) {
						Sentiment s = new Sentiment();
						
						if ("positive".equals(sentiment)) {
							s.setHomeCount(1);
						} else if ("negative".equals(sentiment)) {
							s.setHomeCount(-1);
						}
						
						averageSentiments.put(pos/40, s);
						
						sentiments.put(pos, s);
					} else {
						/*if ("positive".equals((String)(current.get("mySentiment")))) {
							sentiments.get(pos).setHomeCount(sentiments.get(pos).getHomeCount() + 1);
						} else if ("negative".equals((String)(current.get("mySentiment")))) {
							sentiments.get(pos).setHomeCount(sentiments.get(pos).getHomeCount() - 1);
						}*/
						if ("positive".equals(sentiment)) {
							sentiments.get(pos).setHomeCount(sentiments.get(pos).getHomeCount() + 1);
							averageSentiments.get(pos/40).setHomeCount(averageSentiments.get(pos/40).getHomeCount() + 1);
						} else if("negative".equals(sentiment)) {
							sentiments.get(pos).setHomeCount(sentiments.get(pos).getHomeCount() - 1);
							averageSentiments.get(pos/40).setHomeCount(averageSentiments.get(pos/40).getHomeCount() - 1);
						}
						
					}
				}
				
			} else if (("" + awayTeamId).equals((String) (current.get("teamId")))) {
				
				String sentiment = (String) current.get("sentiment_tweets");
				
				if(sentiment != null) {
					if (sentiments.get(pos) == null) {
						Sentiment s = new Sentiment();
						
						if ("positive".equals(sentiment)) {
							s.setAwayCount(1);
						} else if ("negative".equals(sentiment)) {
							s.setAwayCount(-1);
						}
						
						averageSentiments.put(pos/40, s);
						sentiments.put(pos, s);
					} else {
						
						if ("positive".equals(sentiment)) {
							sentiments.get(pos).setAwayCount(sentiments.get(pos).getAwayCount() + 1);
							averageSentiments.get(pos/40).setAwayCount(averageSentiments.get(pos/40).getAwayCount() + 1);
						} else if("negative".equals(sentiment)) {
							sentiments.get(pos).setAwayCount(sentiments.get(pos).getAwayCount() - 1);
							averageSentiments.get(pos/40).setAwayCount(averageSentiments.get(pos/40).getAwayCount() - 1);
						}
						
					}
				}
				
			}

		}
		//Setting sentiment values in map
		for (Map.Entry<Integer, Sentiment> item : sentiments.entrySet()) {
			Integer key = item.getKey();
			Sentiment value = item.getValue();
			content = content + (0.5 * key) + "," + value.getHomeCount() + "," + value.getAwayCount() + "\r\n";
			listCount.add(value.getHomeCount());
			listCount.add(value.getAwayCount());
		}

		for (Map.Entry<Integer, Sentiment> item : averageSentiments.entrySet()) {
			Integer key = item.getKey();
			Sentiment value = item.getValue();
			content2 = content2 + ((key+1)*20) + "," + value.getHomeCount() + "," + value.getAwayCount() + "\r\n";
			listCount2.add(value.getHomeCount());
			listCount2.add(value.getAwayCount());
		}
		
		writeToFile(content, matchId, "Match");
		writeToFile(content2, matchId, "Average");
		Collections.sort(listCount, (o1, o2) -> Integer.compare(Math.abs(o1), Math.abs(o2)));
		Collections.sort(listCount2, (o1, o2) -> Integer.compare(Math.abs(o1), Math.abs(o2)));
		
		Sentiment sentiment = new Sentiment();
		sentiment.setHomeCount(Math.abs(listCount.get(listCount.size() - 1)));
		sentiment.setAwayCount(Math.abs(listCount2.get(listCount2.size() - 1)));
		return sentiment;
	}

	/**
	 * Method to write the sentiment values to a file
	 * @param fileName 
	 * @param content: the mormalized count for each minute.
	 * @param matchId: id of the match
	 */
	private void writeToFile(String content, int matchId, String fileName) {
		String FILENAME = "C:\\test\\"+fileName + matchId + ".csv";

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private float getMaximumTime(String fileName) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		float maxTime = 0;
		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String[] country = line.split(cvsSplitBy);
				maxTime = Float.parseFloat(country[0]);
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
		return maxTime;
	}

	/**
	 * This method is called to fetch real time data of the live match
	 * @param matchId: id of the live match
	 * @param homeTeamId: id of the home team
	 * @param awayTeamId: id of the away team
	 * @param time: current time
	 * @param normalize: normalized sentiment value
	 * @return
	 */
	public DataPointsModel getRealTimeMatchDetails(int matchId, int homeTeamId, int awayTeamId, float time,
			float normalize) {
		MongoClient mongo = ConnectionFactory.CONNECTION.getClient();
		DB db = mongo.getDB("cdpTweets");

		//fetching schedule details to determine the target database
		DBCollection scheduleCollection = db.getCollection("schedule");
		String databaseName = "";

		BasicDBObject query = new BasicDBObject();
		query.put("matchId", matchId);

		DBCursor scheduleCursor = scheduleCollection.find(query);
		Date matchDate = null;
		while (scheduleCursor.hasNext()) {
			DBObject schedule = scheduleCursor.next();
			matchDate = (Date) schedule.get("matchTime");
			Calendar cal = Calendar.getInstance();
			cal.setTime(matchDate);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			databaseName = "tweets-" + year + "-" + month + "-" + day;
			break;
		}

		DBCollection coll = db.getCollection(databaseName);

		List<BasicDBObject> searchArguments = new ArrayList<BasicDBObject>();		
		BasicDBObject searchObject = new BasicDBObject();
		searchArguments.add(new BasicDBObject("timestamp_ms",
				new BasicDBObject("$gt", Long.toString((long) (matchDate.getTime() + (long) (time*60 * 1000) - 30000)))));
		searchArguments.add(new BasicDBObject("timestamp_ms",
				new BasicDBObject("$lt", Long.toString((long) (matchDate.getTime() + (long) (time*60 * 1000))))));
		searchArguments.add(new BasicDBObject("matchId", "" + matchId));
		searchObject.put("$and", searchArguments);

		DBCursor cursor = coll.find(searchObject);

		int homeCount = 0;
		int awayCount = 0;

		while (cursor.hasNext()) {
			DBObject tweet = cursor.next();
			Random rand = new Random();
			int value = rand.nextInt(2);
			
			if ((("" + homeTeamId).equals(tweet.get("teamId")))) {
				if(value == 0) {
					homeCount++;
				} else {
					homeCount--;
				}
				/*if ("positive".equals((String)(tweet.get("mySentiment")))) {
					homeCount++;
				} else if ("negative".equals(tweet.get("mySentiment"))) {
					homeCount--;
				}*/
			} else if ((("" + awayTeamId).equals((String)(tweet.get("teamId"))))) {
				if(value == 0) {
					awayCount++;
				} else {
					awayCount--;
				}
				/*if ("positive".equals((String)(tweet.get("mySentiment")))) {
					awayCount++;
				} else if ("negative".equals((String)(tweet.get("mySentiment")))) {
					awayCount--;
				}*/
			}

		}
		DataPointsModel data = new DataPointsModel();
		data.setX(((float) homeCount) / normalize);
		data.setY(((float) awayCount) / normalize);
		//mongo.close();
		return data;
	
	}
	
}
