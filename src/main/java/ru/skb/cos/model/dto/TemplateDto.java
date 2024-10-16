package ru.skb.cos.model.dto;

import java.util.List;

/**
 *<ul>
 *     <li>name - название шаблона проверки</li>
 *     <li>description - описание шаблона</li>
 *     <li>criterions - список критериев</li>
 *</ul>
 * */
public record TemplateDto (
        String name,
        String description,
        List<CriterionDto> criteria) {
}
