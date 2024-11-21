package ru.skb.cos.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "criteria")
public class CriterionEntity {
    @Id
    @GeneratedValue
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String description;

    public CriterionEntity() {
        super();
    }

    public CriterionEntity(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public CriterionEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CriterionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
