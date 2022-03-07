package com.pactera.weather.util;

import java.io.IOException;

import com.pactera.weather.config.WeatherApiResponse;
import com.pactera.weather.model.WeatherApiData;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.pactera.weather.config.ApiConfig;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class WeatherApiDeserializer extends StdDeserializer<WeatherApiData> {
	private static final long serialVersionUID = 2589574050746889771L;
	
	@Autowired
	private transient ApiConfig apiConfig;
	
	public WeatherApiDeserializer() {
		this(null);
	}

	public WeatherApiDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public WeatherApiData deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException {
		WeatherApiResponse responseConfig = apiConfig.getOpenWeather().getResponse();

		//get value from json node (city,weather,temperature(c),wind(kmh))
		JsonNode node = p.getCodec().readTree(p);
		JsonNode locationNode = node.findPath(responseConfig.getLocationNode());
		JsonNode currentNode = node.findPath(responseConfig.getCurrentNode());
		JsonNode weatherNode =  currentNode.get(responseConfig.getConditionNode());

		String city = getNodeStringValue(locationNode, responseConfig.getCity());
		String weather = getNodeStringValue(weatherNode, responseConfig.getDescription());
		String temperature = getNodeStringValue(currentNode, responseConfig.getTemp());
		String wind = getNodeStringValue(currentNode, responseConfig.getSpeed());

		return new WeatherApiData(city, weather, temperature, wind);
	}
	//get the string value of specified node
	private String getNodeStringValue(JsonNode node, String field) {
		return node.get(field).asText(Constants.NOT_AVAILABLE);
	}

}
