package ru.skb.cos.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "templates")
public class TemplateEntity {
    @Id
    @GeneratedValue
    Integer id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String description;
    @ManyToMany
    List<CriterionEntity> criteria;
}
