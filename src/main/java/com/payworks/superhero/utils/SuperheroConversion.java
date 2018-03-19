package com.payworks.superhero.utils;

import com.payworks.superhero.api.model.SkillDTO;
import com.payworks.superhero.api.model.SuperheroDTO;
import com.payworks.superhero.model.Skill;
import com.payworks.superhero.model.Superhero;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Misc conversion utils for superhero API entities
 */
@Component
public class SuperheroConversion {

    /**
     * Converts a superhero from the db to the internal superhero
     */
    public static SuperheroDTO toDTO(Superhero superhero) {
        SuperheroDTO superheroDTO;
        if (superhero == null) {
            return null;
        }
        BeanUtils.copyProperties(superhero, superheroDTO = new SuperheroDTO());
        if (superhero.getSkills() != null && superhero.getSkills().size() > 0) {
            Set<SkillDTO> skillsSet = superhero.getSkills().stream()
                    .map(SkillConversion::toDTO).collect(Collectors.toSet());
            superheroDTO.setSkills(skillsSet);
        }
        if (superhero.getAllies() != null && superhero.getAllies().size() > 0) {
                Set<SuperheroDTO> allies = superhero.getAllies().stream()
                        .map(SuperheroConversion::toDTO).collect(Collectors.toSet());
            superheroDTO.setAllies(allies);
        }
        return superheroDTO;
    }

    /**
     * Converts a superhero from the db to the internal superhero
     */
    public static Superhero fromDTO(SuperheroDTO dto) {
        Superhero superhero;
        if (dto == null) {
            return null;
        }
        BeanUtils.copyProperties(dto, superhero = new Superhero());
        if (dto.getSkills() != null && dto.getSkills().size() > 0) {
            Set<Skill> skillsSet = dto.getSkills().stream()
                    .map(SkillConversion::fromDTO).collect(Collectors.toSet());
            superhero.setSkills(skillsSet);
        }
        return superhero;
    }
}
