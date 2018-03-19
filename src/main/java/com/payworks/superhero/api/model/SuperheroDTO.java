package com.payworks.superhero.api.model;

import java.util.List;
import java.util.Set;

/**
 * Super hero model
 */
public class SuperheroDTO {

    private long id;
    private String name;
    private String pseudonym;
    private String publisher;
    private Set<SkillDTO> skills;
    private Set<SuperheroDTO> allies;

    public SuperheroDTO() {
    }

    public SuperheroDTO(long id, String name, String pseudonym, String publisher, Set<SkillDTO> skills, Set<SuperheroDTO> allies) {
        this.id = id;
        this.name = name;
        this.pseudonym = pseudonym;
        this.publisher = publisher;
        this.skills = skills;
        this.allies = allies;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Set<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillDTO> skills) {
        this.skills = skills;
    }

    public Set<SuperheroDTO> getAllies() {
        return allies;
    }

    public void setAllies(Set<SuperheroDTO> allies) {
        this.allies = allies;
    }

    @Override
    public String toString() {
        return "SuperheroDTO{" +
                "name='" + name + '\'' +
                ", pseudonym='" + pseudonym + '\'' +
                ", publisher='" + publisher + '\'' +
                ", skills=" + skills +
                ", allies=" + allies +
                '}';
    }
}
