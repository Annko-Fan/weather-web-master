package com.pactera.weather.config;

public class WeatherApiResponse {

	private String locationNode;
	private String currentNode;
	private String conditionNode;
	private String city;
	private String description;
	private String temp_c;
	private String wind_kph;

	public String getLocationNode() {
		return locationNode;
	}

	public void setLocationNode(String locationNode) {
		this.locationNode = locationNode;
	}

	public String getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(String currentNode) {
		this.currentNode = currentNode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTemp() {
		return temp_c;
	}

	public void setTemp(String temp_c) {
		this.temp_c = temp_c;
	}

	public String getSpeed() {
		return wind_kph;
	}

	public void setSpeed(String wind_kph) {
		this.wind_kph = wind_kph;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getConditionNode() {
		return conditionNode;
	}

	public void setConditionNode(String conditionNode) {
		this.conditionNode = conditionNode;
	}

}
