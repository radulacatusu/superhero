package com.payworks.superhero.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(indexes = {
        @Index(columnList = "name", name = "skill_name_idx")}
)
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToMany(mappedBy = "skills")
    private Set<Superhero> superhero = new HashSet<>();

    public Skill() {
    }

    public Skill(long id, String name) {
        this.id = id;
        this.name = name;
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

    public Set<Superhero> getSuperhero() {
        return superhero;
    }

    public void setSuperhero(Set<Superhero> superhero) {
        this.superhero = superhero;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
