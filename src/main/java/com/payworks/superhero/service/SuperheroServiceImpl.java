package com.payworks.superhero.service;

import com.payworks.superhero.api.model.SuperheroDTO;
import com.payworks.superhero.model.Skill;
import com.payworks.superhero.model.Superhero;
import com.payworks.superhero.repository.SuperheroRepository;
import com.payworks.superhero.utils.SkillConversion;
import com.payworks.superhero.utils.SuperheroConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SuperheroServiceImpl implements SuperheroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SuperheroServiceImpl.class);

    @Resource
    private SuperheroRepository superheroRepository;

    @Resource
    private SkillService skillService;

    @Resource
    private SuperheroConversion superheroConversion;


    @Override
    public SuperheroDTO get(String name) {
        return superheroConversion.toDTO(superheroRepository.findByName(name));
    }

    @Override
    public SuperheroDTO create(SuperheroDTO superhero) {
        Superhero existingEntity = superheroRepository.findByName(superhero.getName());
        if (existingEntity == null) {
            Superhero entity = superheroConversion.fromDTO(superhero);
            entity.setSkills(getOrSaveSkills(superhero));
            entity.setAllies(getAllies(superhero));
            existingEntity = superheroRepository.saveAndFlush(entity);
        } else {
            LOGGER.info("Superhero: {} already exists.", superhero);
        }
        return superheroConversion.toDTO(existingEntity);
    }

    @Override
    public List<SuperheroDTO> getSuperheroes() {
        List<com.payworks.superhero.model.Superhero> listSuperheroes = superheroRepository.findAll();
        return listSuperheroes.stream().map(e -> superheroConversion.toDTO(e)).collect(Collectors.toList());
    }

    private Set<Skill> getOrSaveSkills(SuperheroDTO superhero) {
        if (superhero.getSkills() != null && superhero.getSkills().size() > 0) {
            Set<Skill> skillsSet = superhero.getSkills().stream()
                    .map(skillService::getOrCreate).map(SkillConversion::fromDTO).collect(Collectors.toSet());
            return skillsSet;
        }
        return new HashSet<>();
    }

    private Set<Superhero> getAllies(SuperheroDTO superhero) {
        Set<Superhero> existingAllies = new HashSet<>();
        if (superhero.getAllies() != null && superhero.getAllies().size() > 0) {
            for (SuperheroDTO dto : superhero.getAllies()) {
                Superhero existingAlly = superheroRepository.findByName(dto.getName());
                if (existingAlly != null) {
                    existingAllies.add(existingAlly);
                }
            }
        }
        return existingAllies;
    }

}
