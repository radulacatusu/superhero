package com.payworks.superhero.ignore;

import com.payworks.superhero.controller.TestHelper;
import com.payworks.superhero.model.Superhero;
import com.payworks.superhero.repository.SuperheroRepository;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuperheroCreateTest {

    private static final String RESOURCE_URL = "/api/superheroes";
    private static final String PUBLISHER = "Marvel";
    private static final String NAME = "Batman";
    private static final String PSEUDONYM = "ZZZ";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SuperheroRepository superheroRepository;

    @Autowired
    private TestHelper testHelper;

    private Superhero createdSuperhero;

    @After
    public void cleanup() {
        if (null != createdSuperhero) {
            superheroRepository.delete(createdSuperhero);
        }
    }

    @Test
    public void createNewContact() {
        JSONObject postBody = testHelper.constructSuperhero(NAME, PSEUDONYM, PUBLISHER);

        ResponseEntity<Superhero> responseEntity =
                restTemplate.exchange(RESOURCE_URL, HttpMethod.POST, testHelper.getPostRequestHeaders(postBody.toString()),
                        Superhero.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, responseEntity.getHeaders().getContentType());

        createdSuperhero = responseEntity.getBody();
        assertEquals(NAME, createdSuperhero.getName());
        assertEquals(PSEUDONYM, createdSuperhero.getPseudonym());
        assertEquals(PUBLISHER, createdSuperhero.getPublisher());

        // Check Location HeaderURL
        String expectedLocationUrl = testHelper.superheroUrlHelper(RESOURCE_URL, createdSuperhero.getName());
        String returnedLocationUrl = responseEntity.getHeaders().getLocation().toString();

        assertThat(returnedLocationUrl, containsString(expectedLocationUrl));
    }

    @Test
    public void createNewContactWithoutFirstName() {

        JSONObject postBody = testHelper.constructSuperhero(null, PSEUDONYM, PUBLISHER);

        ResponseEntity<Superhero> responseEntity =
                restTemplate.exchange(RESOURCE_URL, HttpMethod.POST, testHelper.getPostRequestHeaders(postBody.toString()),
                        Superhero.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, responseEntity.getHeaders().getContentType());
    }
}
