package com.uottawa.twittervisual.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Timeline implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "player1")
	String player1;

	@JsonProperty(value = "player2")
	String player2;

	@JsonProperty(value = "abbr1")
	String abbr1;

	@JsonProperty(value = "abbr2")
	String abbr2;

	@JsonProperty(value = "score1")
	String score1;

	@JsonProperty(value = "score2")
	String score2;

	@JsonProperty(value = "description")
	String description;

	@JsonProperty(value = "start")
	String start;

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public String getAbbr1() {
		return abbr1;
	}

	public void setAbbr1(String abbr1) {
		this.abbr1 = abbr1;
	}

	public String getAbbr2() {
		return abbr2;
	}

	public void setAbbr2(String abbr2) {
		this.abbr2 = abbr2;
	}

	public String getScore1() {
		return score1;
	}

	public void setScore1(String score1) {
		this.score1 = score1;
	}

	public String getScore2() {
		return score2;
	}

	public void setScore2(String score2) {
		this.score2 = score2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

}
