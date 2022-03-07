package com.pactera.weather.service;

import java.io.IOException;

import com.pactera.weather.model.WeatherApiData;
import com.pactera.weather.model.WeatherApiRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.pactera.weather.config.ApiConfig;
import com.pactera.weather.model.WeatherError;
import com.pactera.weather.util.JsonUtil;

@Service
public class WeatherApiService implements IWeatherService<WeatherApiData, WeatherApiRequest> {
	private final ApiConfig apiConfig;
	private final RestTemplate restTemplate;

	public WeatherApiService(ApiConfig apiConfig, RestTemplate restTemplate) {
		this.apiConfig = apiConfig;
		this.restTemplate = restTemplate;
	}

	//get whole response from weather api
	@Override
	public WeatherApiData getWeatherInfo(WeatherApiRequest request) {
		StringBuilder requestBuider = new StringBuilder();
		requestBuider.append(apiConfig.getOpenWeather().getBaseUrl());
		requestBuider.append(apiConfig.getOpenWeather().getVersionSpec());
		requestBuider.append(apiConfig.getOpenWeather().getParams());

		String query = String.format("%s", request.getCity());
		String apiKey = apiConfig.getOpenWeather().getKey();
		String unit = apiConfig.getOpenWeather().getUnit();

		WeatherApiData data = null;
		
		try {
			data = restTemplate.getForObject(requestBuider.toString(), WeatherApiData.class, apiKey, query, unit);
		} catch (HttpClientErrorException e) {
			
			WeatherError weatherError;
			
			try {
				weatherError = JsonUtil.convertJsonToObject(e.getResponseBodyAsString(), WeatherError.class);
			} catch (IOException e1) {
				weatherError = new WeatherError(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
						apiConfig.getOpenWeather().getDefaultErrorMessage());
			}
			
			data = new WeatherApiData();
			data.setWeatherError(weatherError);
		}

		return data;
	}

}
