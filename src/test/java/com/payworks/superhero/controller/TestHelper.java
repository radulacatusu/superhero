package com.payworks.superhero.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Component
public class TestHelper {


    public HttpEntity getRequestHeaders() {
        List<MediaType> acceptTypes = new ArrayList<>();
        acceptTypes.add(MediaType.APPLICATION_JSON_UTF8);

        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        reqHeaders.setAccept(acceptTypes);

        return new HttpEntity<>("parameters", reqHeaders);
    }

    public HttpEntity getPostRequestHeaders(String jsonPostBody) {
        List<MediaType> acceptTypes = new ArrayList<>();
        acceptTypes.add(MediaType.APPLICATION_JSON_UTF8);

        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        reqHeaders.setAccept(acceptTypes);

        return new HttpEntity<>(jsonPostBody, reqHeaders);
    }

    public String superheroUrlHelper(String resourceUrl, String resourceId) {
        return resourceUrl + "/" + resourceId;
    }

    public JSONObject constructSuperhero(String name, String pseudonym, String publisher) {
        JSONObject superheroBody = new JSONObject();

        try {
            if(null != name) {
                superheroBody.put("name", name);
            }
            superheroBody.put("pseudonym", pseudonym);
            superheroBody.put("publisher", publisher);

            return superheroBody;
        } catch(JSONException e) {
            return null;
        }
    }

}