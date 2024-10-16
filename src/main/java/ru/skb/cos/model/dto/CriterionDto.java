package ru.skb.cos.model.dto;

/**
 *<ul>
 *     <li>name - название критерия для оценки приложения</li>
 *     <li>description - описание критерия</li>
 *</ul>
 * */
public record CriterionDto (
        String name,
        String description) {
}
