package com.payworks.superhero.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(indexes = {
        @Index(columnList = "name", name = "superhero_name_idx")}
)
public class Superhero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String pseudonym;
    private String publisher;

    @ManyToMany
    @JoinTable(
            name = "superhero_skill",
            joinColumns = {@JoinColumn(name = "superhero_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    private Set<Skill> skills = new HashSet<>();

    @ManyToMany
    private Set<Superhero> allies;

    public Superhero() {
    }

    public Superhero(String name, String pseudonym, String publisher, Set<Skill> skills) {
        this(name, pseudonym, publisher, skills, null);
    }

    public Superhero(String name, String pseudonym, String publisher, Set<Skill> skills, Set<Superhero> allies) {
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

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Superhero> getAllies() {
        return allies;
    }

    public void setAllies(Set<Superhero> allies) {
        this.allies = allies;
    }

    @Override
    public String toString() {
        return "Superhero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pseudonym='" + pseudonym + '\'' +
                ", publisher='" + publisher + '\'' +
                ", skills=" + skills +
                ", allies=" + allies +
                '}';
    }
}
