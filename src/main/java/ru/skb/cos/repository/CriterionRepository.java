package ru.skb.cos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.skb.cos.model.entity.CriterionEntity;

@RepositoryRestResource(path = "Criteria")
public interface CriterionRepository extends CrudRepository<CriterionEntity, Integer> {
}
