package com.payworks.superhero.ignore;

import com.payworks.superhero.api.model.SuperheroDTO;
import com.payworks.superhero.controller.TestHelper;
import com.payworks.superhero.model.Superhero;
import com.payworks.superhero.repository.SuperheroRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuperheroesListTest {

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

    private Superhero singleSuperhero;

    @Before
    public void addSingleSuperhero() {
        Superhero superhero = new Superhero();
        superhero.setName(NAME);
        superhero.setPseudonym(PSEUDONYM);
        superhero.setPublisher(PUBLISHER);
        singleSuperhero = superheroRepository.saveAndFlush(superhero);
    }

    @After
    public void afterAllTests() {
        superheroRepository.delete(singleSuperhero);
    }

    @Test
    public void listSuperheroes() {
        ResponseEntity<SuperheroDTO> responseEntity = restTemplate.exchange(RESOURCE_URL, HttpMethod.GET,
                testHelper.getRequestHeaders(), SuperheroDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, responseEntity.getHeaders().getContentType());
    }

    @Test
    public void getSingleSuperhero() {
        String resourceUrl = RESOURCE_URL + singleSuperhero.getName();
        ResponseEntity<SuperheroDTO> responseEntity = restTemplate.exchange(resourceUrl, HttpMethod.GET,
                testHelper.getRequestHeaders(), SuperheroDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, responseEntity.getHeaders().getContentType());

        SuperheroDTO parsedSuperhero = responseEntity.getBody();
        assertEquals(singleSuperhero.getId(), parsedSuperhero.getId());
        assertEquals(singleSuperhero.getName(), parsedSuperhero.getName());
        assertEquals(singleSuperhero.getPublisher(), parsedSuperhero.getPublisher());
    }

    @Test
    public void handleNotFound() {
        String resourceUrl = RESOURCE_URL + "5555";
        ResponseEntity<SuperheroDTO> responseEntity = restTemplate.exchange(resourceUrl, HttpMethod.GET,
                testHelper.getRequestHeaders(), SuperheroDTO.class);

        assertEquals(404, responseEntity.getStatusCodeValue());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, responseEntity.getHeaders().getContentType());
    }
}
