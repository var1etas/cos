package ru.skb.cos.controllers.dto;

import java.util.List;

public record TemplateDto(String name, String description, List<Long> criteriaIdList) {
}
