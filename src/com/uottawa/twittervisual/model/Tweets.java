package com.uottawa.twittervisual.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Tweets implements Serializable {

	@JsonProperty(value = "name")
	String name;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty(value = "children")
	List<Tweets> children;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty(value = "size")
	Integer count;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tweets> getChildren() {
		return children;
	}

	public void setChildren(List<Tweets> children) {
		this.children = children;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
