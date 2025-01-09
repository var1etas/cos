package ru.skb.cos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.skb.cos.model.entity.ProjectEntity;

import java.util.Optional;

@RepositoryRestResource
public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {
    Optional<ProjectEntity> findByName(@Param("name") String name);
}
