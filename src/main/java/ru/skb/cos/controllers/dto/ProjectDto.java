package ru.skb.cos.controllers.dto;

import java.util.Map;

/**
 * Dto проекта, заполняется название, описание и ссылки на материалы по каждому критерию
 * @param name имя
 * @param description описание
 * @param criteria ссылки на материалы
 */
public record ProjectDto(String name, String description, Map<Long, String> criteria) {
}
