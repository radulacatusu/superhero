package com.payworks.superhero.controllers;

import com.payworks.superhero.api.model.SuperheroDTO;
import com.payworks.superhero.service.SuperheroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SuperheroController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SuperheroController.class);

    @Autowired
    private SuperheroService superheroService;

    @RequestMapping(value = "superheroes/{name}", method = RequestMethod.GET)
    public ResponseEntity<SuperheroDTO> getSingleSuperhero(@PathVariable String name) {
        LOGGER.info("--> getSuperhero method called with parameter: " + name);
        SuperheroDTO dto = superheroService.get(name);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        LOGGER.info("Superhero with name: {} does NOT exist.", name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "superheroes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SuperheroDTO> createNewSuperhero(@RequestBody SuperheroDTO superhero, HttpServletRequest req) {
        LOGGER.info("--> createNewSuperhero method called with parameter: " + superhero);
        SuperheroDTO rNew = superheroService.create(superhero);
        return new ResponseEntity<>(rNew, HttpStatus.CREATED);
    }

    @RequestMapping(value = "superheroes", method = RequestMethod.GET)
    public ResponseEntity<List<SuperheroDTO>> getAllSuperheroes() {
        LOGGER.info("--> getAllSuperheroes method called");
        List<SuperheroDTO> all = superheroService.getSuperheroes();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
