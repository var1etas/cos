package ru.skb.cos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.skb.cos.model.entity.CriterionEntity;

import java.util.Optional;

@RepositoryRestResource(path = "Criteria")
public interface CriterionRepository extends CrudRepository<CriterionEntity, Long> {
    Optional<CriterionEntity> findByName(String name);
}
