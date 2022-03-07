package com.pactera.weather.model;

public class WeatherApiRequest extends AbstractWeatherRequest {
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


}
