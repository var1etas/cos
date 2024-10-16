package ru.skb.cos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.skb.cos.model.entity.TemplateEntity;

@RepositoryRestResource(path = "Templates")
public interface TemplateRepository extends CrudRepository<TemplateEntity, Integer> {
}
