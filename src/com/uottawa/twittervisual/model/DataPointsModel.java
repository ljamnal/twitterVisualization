package com.uottawa.twittervisual.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataPointsModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("x")
	float x;
	
	@JsonProperty("y")
	float y;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
}
