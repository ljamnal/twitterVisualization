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

	public int getMatchDetails(int matchId, int homeTeamId, int awayTeamId) {

		MongoClient mongoClient = ConnectionFactory.CONNECTION.getClient();
		DB db = mongoClient.getDB("cdpTweets");
		// DBCollection collection = db.getCollection("agentConfiguration");
		/*
		 * MongoClient mongo = new MongoClient("localhost", 27017); MongoDatabase
		 * database = mongo.getDatabase("cdpTweets");
		 */

		// Retrieving a collection
		/* MongoCollection<Document> collection = db.getCollection("schedule"); */
		DBCollection scheduleCollection = db.getCollection("schedule");
		String databaseName = "";

		BasicDBObject query = new BasicDBObject();
		query.put("matchId", matchId);

		DBCursor scheduleCursor = scheduleCollection.find(query);
		/*
		 * List<Document> employees = (List<Document>) collection.find(eq("matchId",
		 * matchId)) .into(new ArrayList<Document>());
		 */

		// Cursor cursor = collection.find(query);
		Date matchDate = null;
		String fixture = null;
		while (scheduleCursor.hasNext()) {

			DBObject schedule = scheduleCursor.next();
			matchDate = (Date) schedule.get("matchTime");
			fixture = (String) schedule.get("fixture");

			// System.out.println(matchDate.getTime());

			Calendar cal = Calendar.getInstance();
			cal.setTime(matchDate);

			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);

			/*
			 * System.out.println(year); System.out.println(month); System.out.println(day);
			 */
			databaseName = "tweets-" + year + "-" + month + "-" + day;

			System.out.println("tweets-" + year + "-" + month + "-" + day);
			break;
		}

		int max = 0;
		long currentTime = new Date().getTime();
		int a = 1;
		if (currentTime > matchDate.getTime()) {
			DBCollection coll = db.getCollection(databaseName);
			// MongoCollection<Document> coll = database.getCollection(databaseName);

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

			/*
			 * BasicDBObject query = new BasicDBObject(); query.put("matchId", ""+matchId);
			 * query.put("timestamp_ms", new
			 * BasicDBObject("$gt",Long.toString(matchDate.getTime())));
			 */// query.put("timestamp_ms", new
				// BasicDBObject("$lt",Long.toString(matchDate.getTime() + 1000*60)));
			System.out.println(matchDate.getTime());
			System.out.println(matchDate.getTime() + 60000);

			System.out.println("query start");
			System.out.println(new Date());
			// List<Document> tweets = (List<Document>) coll.find(eq("matchId", "" +
			// matchId)).batchSize(50000)
			// .into(new ArrayList<Document>());
			DBCursor cursor = coll.find(searchObject).batchSize(10000);
			// List<DBObject> tweets = cursor.toArray();
			System.out.println("matchId:" + matchId);
			System.out.println("query end");
			System.out.println(new Date());

			// System.out.println("total tweets:" + tweets.size());
			String fileName = "C:\\test\\Match" + matchId + ".csv";
			File f = new File(fileName);
			if (f.exists() && !f.isDirectory() && a == 0) {
				float maxTime = getMaximumTime(fileName);
				long minimumTime = (long) (matchDate.getTime() + 1000 * 60 * maxTime + 1000 * 30);
				max = countSentiment(maximum, minimumTime, matchId, matchDate, cursor, homeTeamId, awayTeamId);
			} else {

				long minimumTime = (long) (matchDate.getTime() + 1000 * 30);
				max = countSentiment(maximum, matchDate.getTime(), matchId, matchDate, cursor, homeTeamId, awayTeamId);
			}
			cursor.close();
		}
		// mongo.close();
		scheduleCursor.close();
		// mongoClient.close();
		return max;
	}

	private int countSentiment(long currentTime, long matchTime, int matchId, Date matchDate, DBCursor cursor,
			int homeTeamId, int awayTeamId) {

		String content = "";
		long min = matchTime;
		long max = currentTime;

		Map<Integer, Sentiment> sentiments = new HashMap<>();
		List<Integer> listCount = new ArrayList<>();
		int j = 0;
		long matchEndTime = matchDate.getTime() + 100 * 60 * 1000;
		int c = 0;
		int co = 0;
		/*
		 * for (long i = min; i <= max; i = i + 1000 * 30) {
		 * 
		 * if (i > matchEndTime) { break; }
		 * 
		 * int homeCount = 0; int awayCount = 0;
		 * 
		 * long lowerLimit = i - 1000 * 30;
		 */ Date d = new Date();
		long maximum = 0l;
		int lal = 0, lal2 = 0;
		long minimum = 99999999999999l;
		System.out.println(new Date());
		while (cursor.hasNext()) {
			DBObject current = cursor.next();
			long tweetTimestamp = Long.parseLong((String) current.get("timestamp_ms"));
			System.out.println((++c) + " - " + ((String) current.get("timestamp_ms")));

			int pos = (int) (Math.ceil((tweetTimestamp - matchTime) / 30000));

			Random rand = new Random();
			int value = rand.nextInt(2);
			if (("" + homeTeamId).equals((String) (current.get("teamId")))) {

				if (sentiments.get(pos) == null) {
					Sentiment s = new Sentiment();
					if (value == 0) {
						s.setHomeCount(1);
					} else {
						s.setHomeCount(-1);
					}
					sentiments.put(pos, s);
				} else {
					if (value == 0) {
						sentiments.get(pos).setHomeCount(sentiments.get(pos).getHomeCount() + 1);
					} else {
						sentiments.get(pos).setHomeCount(sentiments.get(pos).getHomeCount() - 1);
					}
				}
			} else if (("" + awayTeamId).equals((String) (current.get("teamId")))) {
				if (sentiments.get(pos) == null) {
					Sentiment s = new Sentiment();
					if (value == 0) {
						s.setAwayCount(1);
					} else {
						s.setAwayCount(-1);
					}
					sentiments.put(pos, s);
				} else {
					if (value == 0) {
						sentiments.get(pos).setAwayCount(sentiments.get(pos).getAwayCount() + 1);
					} else {
						sentiments.get(pos).setAwayCount(sentiments.get(pos).getAwayCount() - 1);
					}
				}
			}

			if (tweetTimestamp > maximum) {
				maximum = tweetTimestamp;
			}
			if (tweetTimestamp < minimum) {
				minimum = tweetTimestamp;
			}

			if (tweetTimestamp < matchDate.getTime() || tweetTimestamp > (matchDate.getTime() + 1000 * 60 * 100)) {
				lal++;
			}
			if (tweetTimestamp > matchDate.getTime() && tweetTimestamp < (matchDate.getTime() + 1000 * 60 * 100)) {
				lal2++;
			}
			/*
			 * //System.out.println("tweet:" + (++c)); DBObject current = cursor.next();
			 * 
			 * long tweetTimestamp = Long.parseLong((String) current.get("timestamp_ms"));
			 * 
			 * if(tweetTimestamp > matchTime && tweetTimestamp < currentTime) {
			 * //System.out.println("match:" + (++c)); } else if (tweetTimestamp >
			 * currentTime || tweetTimestamp > matchEndTime) {
			 * //System.out.println(current); break; } else if(tweetTimestamp < matchTime) {
			 * //System.out.println("before:" + (++co)); }
			 */
		}
		System.out.println(new Date());
		System.out.println(c);
		System.out.println(matchTime);
		System.out.println(matchDate.getTime());
		System.out.println(maximum);
		System.out.println(minimum);
		System.out.println(lal);
		System.out.println(lal2);
		/*
		 * j++; content = content + 0.5 * j + "," + homeCount + "," + awayCount +
		 * "\r\n"; listCount.add(homeCount); listCount.add(awayCount);
		 *//*
			 * System.out.println(30 * j); System.out.println(homeCount);
			 * System.out.println(awayCount);
			 */
		/* } */

		for (Map.Entry<Integer, Sentiment> item : sentiments.entrySet()) {
			Integer key = item.getKey();
			Sentiment value = item.getValue();
			content = content + (0.5 * key) + "," + value.getHomeCount() + "," + value.getAwayCount() + "\r\n";
			listCount.add(value.getHomeCount());
			listCount.add(value.getAwayCount());
		}

		System.out.println("match final:" + (++c));
		System.out.println("before final:" + (++co));
		System.out.println(d);
		System.out.println(new Date());
		writeToFile(content, matchId);
		System.out.println("count list " + listCount.size());
		Collections.sort(listCount, (o1, o2) -> Integer.compare(Math.abs(o1), Math.abs(o2)));

		System.out.println(listCount);
		System.out.println(Math.abs(listCount.get(listCount.size() - 1)));
		return Math.abs(listCount.get(listCount.size() - 1));
	}

	private void writeToFile(String content, int matchId) {
		String FILENAME = "C:\\test\\Match" + matchId + ".csv";

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);

			// System.out.println("Done");
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

	public DataPointsModel getRealTimeMatchDetails(int matchId, int homeTeamId, int awayTeamId, float time,
			float normalize) {
		MongoClient mongo = ConnectionFactory.CONNECTION.getClient();
		DB db = mongo.getDB("cdpTweets");

		DBCollection scheduleCollection = db.getCollection("schedule");
		String databaseName = "";

		BasicDBObject query = new BasicDBObject();
		query.put("matchId", matchId);

		DBCursor scheduleCursor = scheduleCollection.find(query);

		Date matchDate = null;

		while (scheduleCursor.hasNext()) {

			DBObject schedule = scheduleCursor.next();
			matchDate = (Date) schedule.get("matchTime");

			// System.out.println(matchDate.getTime());

			Calendar cal = Calendar.getInstance();
			cal.setTime(matchDate);

			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);

			/*
			 * System.out.println(year); System.out.println(month); System.out.println(day);
			 */
			databaseName = "tweets-" + year + "-" + month + "-" + day;

			System.out.println("tweets-" + year + "-" + month + "-" + day);
			break;
		}

		DBCollection coll = db.getCollection(databaseName);

		List<BasicDBObject> searchArguments = new ArrayList<BasicDBObject>();
//		System.out.println(minute);
		System.out.println(Long.toString((long) (matchDate.getTime() + (long) (time*60 * 1000) - 30000)));
		System.out.println(Long.toString((long) (matchDate.getTime() + (long) (time*60 * 1000))));
		
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
			//long tweetTimestamp = Long.parseLong((String) (tweet.get("timestamp_ms")));

			System.out.println(tweet.get("created_at"));
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

		System.out.println("Minute:" + time);
		System.out.println("HC:" + homeCount);
		System.out.println("AC:" + awayCount);
		// mongo.close();
		return data;
	}
}
