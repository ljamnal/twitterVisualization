package com.uottawa.twittervisual.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Tweets implements Serializable{
	
	@JsonProperty(value = "name")
	String name;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty(value = "positive")
	Integer positive;
	
	
	
	
}
