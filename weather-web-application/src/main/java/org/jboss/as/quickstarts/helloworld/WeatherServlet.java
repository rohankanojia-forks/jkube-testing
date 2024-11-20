/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.helloworld;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/getWeather")
public class WeatherServlet extends HttpServlet {
    private static final String OPENWEATHERMAP_WEATHER = "http://api.openweathermap.org/data/2.5/weather";
    public static final String CITY = "getCity";
    private static final String LAT = "lat";
    private static final String LON = "lon";
    private static final String OPENWEATHERMAP_CITY_PARAM = "q";
    private static final String OPENWEATHERMAP_APPID_PARAM = "appid";
    public static final String APITOKEN_ENV_VAR = "OPENWEATHER_API_KEY";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> queryParamMap = getQueryParameters(req);
        String response = performGetToWeatherAPI(queryParamMap);

        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.println(response);
        writer.close();
    }

    private String performGetToWeatherAPI(Map<String, String> queryParamMap) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl httpUrl = getWeatherQueryUrl(queryParamMap);
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                return "{\"message\": \"Nothing received from OpenWeather API. url: "+httpUrl.toString() +"\", }";
            }
        } catch (IOException exception) {
            return "{\"message\": \"error in connecting to OpenWeather API. url:" + httpUrl.toString() + " " + exception.getMessage() + "}";
        }
    }

    private HttpUrl getWeatherQueryUrl(Map<String, String> queryParamMap) {
        HttpUrl.Builder httpUrlBuilder = HttpUrl.get(OPENWEATHERMAP_WEATHER).newBuilder();
        if (queryParamMap.containsKey(CITY)) {
            httpUrlBuilder.addQueryParameter(OPENWEATHERMAP_CITY_PARAM, queryParamMap.get(CITY));
        }
        if (queryParamMap.containsKey(LAT) && queryParamMap.containsKey(LON)) {
            httpUrlBuilder.addQueryParameter(LAT, queryParamMap.get(LAT));
            httpUrlBuilder.addQueryParameter(LON, queryParamMap.get(LON));
        }
        httpUrlBuilder.addQueryParameter(OPENWEATHERMAP_APPID_PARAM, System.getenv(APITOKEN_ENV_VAR));
        return httpUrlBuilder.build();
    }

    private Map<String, String> getQueryParameters(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();
        String queryParameterString = request.getQueryString();
        String[] queryParameterStringParts = queryParameterString.split("&");
        for (String queryParameterStringPart : queryParameterStringParts) {
            String[] singleQueryParameterParts = queryParameterStringPart.split("=");
            result.put(singleQueryParameterParts[0], singleQueryParameterParts[1]);
        }
        return result;
    }
}
