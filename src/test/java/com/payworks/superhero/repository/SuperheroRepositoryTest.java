package com.payworks.superhero.repository;

import com.payworks.superhero.model.Skill;
import com.payworks.superhero.model.Superhero;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class SuperheroRepositoryTest extends AbstractInMemoryDbTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SuperheroRepository superheroRepository;

    @Test
    public void whenFindByName_thenReturnSkill() {
        // given
        Superhero newSuperhero = new Superhero();
        newSuperhero.setName("Batman");
        entityManager.persist(newSuperhero);
        entityManager.flush();

        // when
        Superhero found = superheroRepository.findByName(newSuperhero.getName());

        // then
        assertThat(found.getName()).isEqualTo(newSuperhero.getName());

        Set<Superhero> allies = new HashSet<>();
        allies.add(found);

        Superhero allySuperhero = new Superhero();
        allySuperhero.setName("Robin");
        allySuperhero.setAllies(allies);
        entityManager.persist(allySuperhero);
        entityManager.flush();

        Superhero foundNext = superheroRepository.findByName(allySuperhero.getName());
        assertThat(foundNext.getName()).isEqualTo(allySuperhero.getName());
    }
}
