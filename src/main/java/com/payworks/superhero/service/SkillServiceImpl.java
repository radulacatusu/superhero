package com.payworks.superhero.service;

import com.payworks.superhero.api.model.SkillDTO;
import com.payworks.superhero.model.Skill;
import com.payworks.superhero.repository.SkillRepository;
import com.payworks.superhero.utils.SkillConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service
public class SkillServiceImpl implements SkillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillServiceImpl.class);

    @Resource
    private SkillRepository skillRepository;

    @Resource
    private SkillConversion skillConversion;

    @Override
    public SkillDTO getOrCreate(SkillDTO dto) {
        Skill existingEntity = skillRepository.findByName(dto.getName());
        if (existingEntity == null) {
            Skill entity = skillConversion.fromDTO(dto);
            existingEntity = skillRepository.saveAndFlush(entity);
        } else {
            LOGGER.debug("Skill: {} already exists.", existingEntity);
        }
        return skillConversion.toDTO(existingEntity);
    }
}
