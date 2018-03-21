package com.payworks.superhero.api.model;

/**
 * SkillDTO model
 */
public class SkillDTO {

    private long id;

    private String name;

    public SkillDTO() {
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

    @Override
    public String toString() {
        return "SkillDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
