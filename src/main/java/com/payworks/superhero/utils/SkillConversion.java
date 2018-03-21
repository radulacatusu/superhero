package com.payworks.superhero.utils;

import com.payworks.superhero.api.model.SkillDTO;
import com.payworks.superhero.model.Skill;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Misc conversion utils for SkillDTO API entities
 */
@Component
public class SkillConversion {

    /**
     * Converts a SkillDTO from the db to the internal SkillDTO
     */
    public static SkillDTO toDTO(Skill dto) {
        SkillDTO skill;
        if (dto == null) {
            return null;
        }
        BeanUtils.copyProperties(dto, skill = new SkillDTO());

        return skill;
    }

    /**
     * Converts a superhero from the db to the internal superhero
     */
    public static Skill fromDTO(SkillDTO dto) {
        Skill skill;
        if (dto == null) {
            return null;
        }
        BeanUtils.copyProperties(dto, skill = new Skill());

        return skill;
    }
}
