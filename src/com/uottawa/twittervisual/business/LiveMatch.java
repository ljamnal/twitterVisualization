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
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.uottawa.twittervisual.model.DataPointsModel;

@Component
public class LiveMatch {

	public int getMatchDetails(int matchId, int homeTeamId, int awayTeamId) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase database = mongo.getDatabase("cdpTweets");

		// Retrieving a collection
		MongoCollection<Document> collection = database.getCollection("schedule");

		String databaseName = "";
		List<Document> employees = (List<Document>) collection.find(eq("matchId", matchId))
				.into(new ArrayList<Document>());

		// Cursor cursor = collection.find(query);
		Date matchDate = null;
		String fixture = null;
		for (Document emp : employees) {

			matchDate = (Date) emp.get("matchTime");
			fixture = (String) emp.get("fixture");

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
			MongoCollection<Document> coll = database.getCollection(databaseName);

			List<Document> tweets = (List<Document>) coll.find(eq("matchId", "" + matchId))
					.into(new ArrayList<Document>());

			String fileName = "E:\\test\\Match" + matchId + ".csv";
			File f = new File(fileName);
			if (f.exists() && !f.isDirectory() && a == 0) {
				float maxTime = getMaximumTime(fileName);
				long minimumTime = (long) (matchDate.getTime() + 1000 * 60 * maxTime + 1000 * 30);
				max = countSentiment(currentTime, minimumTime, matchId, matchDate, tweets, homeTeamId, awayTeamId);
			} else {

				long minimumTime = (long) (matchDate.getTime() + 1000 * 30);
				max = countSentiment(currentTime, minimumTime, matchId, matchDate, tweets, homeTeamId, awayTeamId);
			}
		}
		mongo.close();
		return max;
	}

	private int countSentiment(long currentTime, long minimumTime, int matchId, Date matchDate, List<Document> tweets,
			int homeTeamId, int awayTeamId) {

		String content = "";
		long min = minimumTime;
		long max = currentTime;

		List<Integer> listCount = new ArrayList<>();
		int j = 0;
		long matchEndTime = matchDate.getTime() + 100 * 60 * 1000;

		for (long i = min; i <= max; i = i + 1000 * 30) {

			if (i > matchEndTime) {
				break;
			}

			int homeCount = 0;
			int awayCount = 0;

			long lowerLimit = i - 1000 * 30;

			for (Document tweet : tweets) {
				long tweetTimestamp = Long.parseLong(tweet.getString("timestamp_ms"));

				if (tweetTimestamp > lowerLimit && tweetTimestamp < i) {
					if (tweet.getString("teamId").equals("" + homeTeamId)) {
						if ("positive".equals(tweet.getString("mySentiment"))) {
							homeCount++;
						} else if ("negative".equals(tweet.getString("mySentiment"))) {
							homeCount--;
						}
					} else if (tweet.getString("teamId").equals("" + awayTeamId)) {
						if ("positive".equals(tweet.getString("mySentiment"))) {
							awayCount++;
						} else if ("negative".equals(tweet.getString("mySentiment"))) {
							awayCount--;
						}
					}
				}
			}
			j++;
			content = content + 0.5 * j + "," + homeCount + "," + awayCount + "\n";
			listCount.add(homeCount);
			listCount.add(awayCount);
			/*
			 * System.out.println(30 * j); System.out.println(homeCount);
			 * System.out.println(awayCount);
			 */
		}
		writeToFile(content, matchId);

		Collections.sort(listCount, (o1, o2) -> Integer.compare(Math.abs(o1), Math.abs(o2)));

		return Math.abs(listCount.get(listCount.size() - 1));
	}

	private void writeToFile(String content, int matchId) {
		String FILENAME = "E:\\test\\Match" + matchId + ".csv";

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
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase database = mongo.getDatabase("cdpTweets");

		MongoCollection<Document> collection = database.getCollection("schedule");

		String databaseName = "";
		List<Document> employees = (List<Document>) collection.find(eq("matchId", matchId))
				.into(new ArrayList<Document>());
		Date matchDate = null;

		for (Document emp : employees) {

			matchDate = (Date) emp.get("matchTime");

			// System.out.println(matchDate);

			Calendar cal = Calendar.getInstance();
			cal.setTime(matchDate);

			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);

			databaseName = "tweets-" + year + "-" + month + "-" + day;

			// System.out.println("tweets-" + year + "-" + month + "-" + day);
			break;
		}

		MongoCollection<Document> coll = database.getCollection(databaseName);
		List<Document> tweets = (List<Document>) coll.find(eq("matchId", "" + matchId)).into(new ArrayList<Document>());

		long max = (long) (matchDate.getTime() + 1000 * 60 * time);
		long min = max - 1000 * 30;

		int homeCount = 0;
		int awayCount = 0;

		for (Document tweet : tweets) {
			long tweetTimestamp = Long.parseLong(tweet.getString("timestamp_ms"));

			if (tweetTimestamp > min && tweetTimestamp <= max) {

				if (tweet.getString("teamId").equals("" + homeTeamId)) {
					if ("positive".equals(tweet.getString("mySentiment"))) {
						homeCount++;
					} else if ("negative".equals(tweet.getString("mySentiment"))) {
						homeCount--;
					}
				} else if (tweet.getString("teamId").equals("" + awayTeamId)) {
					if ("positive".equals(tweet.getString("mySentiment"))) {
						awayCount++;
					} else if ("negative".equals(tweet.getString("mySentiment"))) {
						awayCount--;
					}
				}
			}
		}
		DataPointsModel data = new DataPointsModel();
		data.setX(((float) homeCount) / normalize);
		data.setY(((float) awayCount) / normalize);

		System.out.println("Minute:" + time);
		System.out.println("HC:" + homeCount);
		System.out.println("AC:" + awayCount);
		mongo.close();
		return data;
	}
}