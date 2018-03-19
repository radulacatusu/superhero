package com.payworks.superhero.repository;

import com.payworks.superhero.model.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long> {

    /**
     * @param name
     * @return
     */
    @Query("SELECT t FROM Superhero t WHERE t.name = ?1")
    Superhero findByName(String name);
}
