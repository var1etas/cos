package ru.skb.cos.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ProjectDetailsDto(Long id, String name, String description, @JsonProperty("criteria") List<ProjectCriterionDto> criteria) {
}
