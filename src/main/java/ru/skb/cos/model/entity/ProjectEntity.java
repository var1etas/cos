package ru.skb.cos.model.entity;

import jakarta.persistence.*;
import ru.skb.cos.controllers.dto.ProjectCriterionDto;
import ru.skb.cos.controllers.dto.ProjectDetailsDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
public class ProjectEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @ElementCollection
    @CollectionTable(name="project_criteria", joinColumns = @JoinColumn(name = "project_id"))
    @MapKeyColumn(name = "id")
    @Column(name = "link")
    private Map<CriterionEntity, String> criteria;

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

    public Map<CriterionEntity, String> getCriteria() {
        return criteria;
    }

    public void setCriteria(Map<CriterionEntity, String> criteria) {
        this.criteria = criteria;
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", criteria=" + criteria +
                '}';
    }

    public ProjectDetailsDto toDto() {
        List<ProjectCriterionDto> criteriaDto = criteria.entrySet().stream()
                .map(entry -> new ProjectCriterionDto(
                        entry.getKey().getId(),
                        entry.getKey().getName(),
                        entry.getKey().getDescription(),
                        entry.getValue()
                ))
                .collect(Collectors.toList());

        return new ProjectDetailsDto(id, name, description, criteriaDto);
    }
}
