package ru.skb.cos.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "templates")
public class TemplateEntity {
    @Id
    @GeneratedValue
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String description;
    @OneToMany
    List<CriterionEntity> criteria;

    public TemplateEntity(Long id, String name, String description, List<CriterionEntity> criteria) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.criteria = criteria;
    }

    public TemplateEntity(String name, String description, List<CriterionEntity> criteria) {
        this.name = name;
        this.description = description;
        this.criteria = criteria;
    }

    public TemplateEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CriterionEntity> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<CriterionEntity> criteria) {
        this.criteria = criteria;
    }
}
