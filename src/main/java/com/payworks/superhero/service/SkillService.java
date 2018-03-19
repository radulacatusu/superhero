package com.payworks.superhero.service;

import com.payworks.superhero.api.model.SkillDTO;

/**
 *
 */
public interface SkillService {

    /**
     * @param dto
     * @return
     */
    SkillDTO getOrCreate(SkillDTO dto);
}
