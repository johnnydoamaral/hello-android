package com.johnny.helloandroid.service;

import android.text.Html;

import com.johnny.helloandroid.model.ChuckNorrisJoke;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class JokeService {

    private static final String URL = "http://api.icndb.com/jokes/random";
    private RestTemplate template = new RestTemplate();

    //IMPORTANT: for this to work, you need to add internet permission (android.permission.INTERNET)
    // in the manifest file. See manifest file for reference
    public String getRandomJoke() {
        template.getMessageConverters().add(new GsonHttpMessageConverter());
        ChuckNorrisJoke joke = template.getForObject(URL, ChuckNorrisJoke.class);
        return Html.fromHtml(joke.getJoke()).toString();
    }
}
