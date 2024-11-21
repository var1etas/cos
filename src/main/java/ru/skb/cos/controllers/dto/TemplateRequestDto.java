package ru.skb.cos.controllers.dto;

import java.util.List;

public record TemplateRequestDto(String name, String description, List<Long> criteriaIdList) {
}
