package com.payworks.superhero.service;

import com.payworks.superhero.api.model.SkillDTO;
import com.payworks.superhero.model.Skill;
import com.payworks.superhero.repository.SkillRepository;
import com.payworks.superhero.utils.SkillConversion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class SkillServiceImplTest {

    @Autowired
    private SkillService skillService;

    @MockBean
    private SkillRepository skillRepository;

    @Before
    public void setUp() {
        Skill existingSkill = new Skill();
        existingSkill.setName("SkillOne");

        Mockito.when(skillRepository.findByName(existingSkill.getName()))
                .thenReturn(existingSkill);

        Skill newSkill = new Skill();
        newSkill.setName("SkillTwo");

        Mockito.when(skillRepository.saveAndFlush(Matchers.any()))
                .thenReturn(newSkill);
    }

    @Test
    public void whenGetOrCreate_thenSkillShouldBeFound() {
        SkillDTO dto = new SkillDTO();
        dto.setName("SkillOne");
        SkillDTO found = skillService.getOrCreate(dto);
        assertThat(found.getName())
                .isEqualTo(dto.getName());
    }

    @Test
    public void whenGetOrCreate_thenSkillShouldBeCreated() {
        SkillDTO dto = new SkillDTO();
        dto.setName("SkillTwo");
        SkillDTO found = skillService.getOrCreate(dto);
        assertThat(found.getName())
                .isEqualTo(dto.getName());
    }

    @TestConfiguration
    static class SkillServiceImplTestContextConfiguration {

        @Bean
        public SkillService skillService() {
            return new SkillServiceImpl();
        }

        @Bean
        public SkillConversion skillConversion() {
            return new SkillConversion();
        }
    }
}
