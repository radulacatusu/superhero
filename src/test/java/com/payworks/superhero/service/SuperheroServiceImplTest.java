package com.payworks.superhero.service;

import com.payworks.superhero.api.model.SuperheroDTO;
import com.payworks.superhero.model.Superhero;
import com.payworks.superhero.repository.SuperheroRepository;
import com.payworks.superhero.utils.SuperheroConversion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableWebMvc
public class SuperheroServiceImplTest {

    @Autowired
    private SuperheroService superheroService;

    @MockBean
    private SuperheroRepository superheroRepository;

    @Before
    public void setUp() {
        Superhero existingSkill = new Superhero();
        existingSkill.setName("Batman");

        Mockito.when(superheroRepository.findByName(existingSkill.getName()))
                .thenReturn(existingSkill);

        Superhero newSkill = new Superhero();
        newSkill.setName("Robin");

        Mockito.when(superheroRepository.saveAndFlush(Matchers.any()))
                .thenReturn(newSkill);
    }

    @Test
    public void whenGet_thenSuperheroShouldBeFound() {
        SuperheroDTO dto = new SuperheroDTO();
        dto.setName("Batman");
        SuperheroDTO found = superheroService.get(dto.getName());
        assertThat(found.getName())
                .isEqualTo(dto.getName());
    }

    @Test
    public void whenCreate_thenSuperheroShouldBeCreated() {
        SuperheroDTO dto = new SuperheroDTO();
        dto.setName("Robin");
        SuperheroDTO found = superheroService.create(dto);
        assertThat(found.getName())
                .isEqualTo(dto.getName());
    }

    @TestConfiguration
    static class SuperheroServiceImplTestContextConfiguration {

        @Bean
        public SuperheroService superheroService() {
            return new SuperheroServiceImpl();
        }

        @Bean
        public SkillService skillService() {
            return new SkillServiceImpl();
        }

        @Bean
        public SuperheroConversion superheroConversion() {
            return new SuperheroConversion();
        }
    }
}
