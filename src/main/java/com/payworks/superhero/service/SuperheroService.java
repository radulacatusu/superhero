package com.payworks.superhero.service;

import com.payworks.superhero.api.model.SuperheroDTO;

import java.util.List;

/**
 *
 */
public interface SuperheroService {

    /**
     * @param name
     * @return
     */
    SuperheroDTO get(String name);

    /**
     * @param superhero
     * @return
     */
    SuperheroDTO create(SuperheroDTO superhero);

    /**
     * @return
     */
    List<SuperheroDTO> getSuperheroes();
}
