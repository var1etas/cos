package ru.skb.cos.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "criteria")
public class CriterionEntity {
    @Id
    @GeneratedValue
    Integer id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String description;
}
