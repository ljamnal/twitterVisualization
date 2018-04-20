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
	int y;
	
	public void setX(float x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
}
