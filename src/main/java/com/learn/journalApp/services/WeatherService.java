package com.learn.journalApp.services;

import com.learn.journalApp.APIResponse.WeatherResponse;
import com.learn.journalApp.cache.AppCache;
import com.learn.journalApp.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather_key_api}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("Weather_of_" + city, WeatherResponse.class);
        if(weatherResponse!=null){
            return weatherResponse;
        }
        else{
            String restApi = appCache.APP_CACHE.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.API_KEY, apiKey).replace(PlaceHolders.CITY, city);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(restApi, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body!=null){
                redisService.set("Weather_of_" + city, body, 300l);
            }
            return body;
        }

    }

}
