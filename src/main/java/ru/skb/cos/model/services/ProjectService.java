package ru.skb.cos.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skb.cos.model.entity.ProjectEntity;
import ru.skb.cos.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectEntity createOrUpdate(ProjectEntity projectEntity) {
        if(projectRepository.findByName(projectEntity.getName()).isPresent()) {
            return null;
        }
        return projectRepository.save(projectEntity);
    }

    public ProjectEntity findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public boolean deleteByName(String name) {
        Optional<ProjectEntity> projectEntityOptional = projectRepository.findByName(name);
        if (projectEntityOptional.isEmpty()) {
            return false;
        }
        projectRepository.delete(projectEntityOptional.get());
        return true;
    }

    public List<ProjectEntity> findAll() {
        List<ProjectEntity> projectEntityList = new ArrayList<>();
        projectRepository.findAll().forEach(projectEntityList::add);
        return projectEntityList;
    }
}
