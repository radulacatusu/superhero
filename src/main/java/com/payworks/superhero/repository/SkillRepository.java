package com.payworks.superhero.repository;

import com.payworks.superhero.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    /**
     * @param name
     * @return
     */
    @Query("SELECT t FROM Skill t WHERE t.name = ?1")
    Skill findByName(String name);
}
