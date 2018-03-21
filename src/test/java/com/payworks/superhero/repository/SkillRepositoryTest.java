package com.payworks.superhero.repository;

import com.payworks.superhero.model.Skill;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class SkillRepositoryTest extends AbstractInMemoryDbTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SkillRepository skillRepository;

    @Test
    public void whenFindByName_thenReturnSkill() {
        // given
        Skill newSkill = new Skill();
        newSkill.setName("Skill One");
        entityManager.persist(newSkill);
        entityManager.flush();

        // when
        Skill found = skillRepository.findByName(newSkill.getName());

        // then
        assertThat(found.getName()).isEqualTo(newSkill.getName());
    }
}
