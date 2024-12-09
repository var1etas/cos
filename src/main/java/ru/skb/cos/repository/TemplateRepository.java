package ru.skb.cos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.skb.cos.model.entity.TemplateEntity;

import java.util.Optional;

@RepositoryRestResource(path = "Templates")
public interface TemplateRepository extends CrudRepository<TemplateEntity, Long> {
    Optional<TemplateEntity> findByName(String name);
}
