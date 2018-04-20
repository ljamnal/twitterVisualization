package com.uottawa.twittervisual.model;

public class FixtureDetail {

	String teams;

	String stadium;

	String matchTime;

	int matchId;

	int homeTeamId;

	int awayTeamId;

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int getHomeTeamId() {
		return homeTeamId;
	}

	public void setHomeTeamId(int homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public int getAwayTeamId() {
		return awayTeamId;
	}

	public void setAwayTeamId(int awayTeamId) {
		this.awayTeamId = awayTeamId;
	}

	public String getTeams() {
		return teams;
	}

	public void setTeams(String teams) {
		this.teams = teams;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public String getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}
}