package ru.skb.cos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skb.cos.controllers.dto.ProjectDto;
import ru.skb.cos.controllers.dto.ProjectDetailsDto;
import ru.skb.cos.model.entity.CriterionEntity;
import ru.skb.cos.model.entity.ProjectEntity;
import ru.skb.cos.model.services.CriterionService;
import ru.skb.cos.model.services.ProjectService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    ProjectService projectService;
    CriterionService criterionService;
    @Autowired
    public ProjectController(ProjectService projectService, CriterionService criterionService) {
        this.projectService = projectService;
        this.criterionService = criterionService;
    }

    @PostMapping
    public ResponseEntity<String> createProject(@RequestBody ProjectDto projectDto) {
        ProjectEntity projectEntity = new ProjectEntity();
        Map<CriterionEntity, String> criterionMap = new HashMap<>();
        for(Map.Entry<Long, String> criterion: projectDto.criteria().entrySet()) {
            Optional<CriterionEntity> criterionEntity = criterionService.getCriterionById(criterion.getKey());
            if(criterionEntity.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            criterionMap.put(criterionEntity.get(), criterion.getValue());
        }
        projectEntity.setName(projectDto.name());
        projectEntity.setDescription(projectDto.description());
        projectEntity.setCriteria(criterionMap);
        if(projectService.createOrUpdate(projectEntity) == null){
            return new ResponseEntity<>("Указаное имя прооекта занято", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{projectId}")
    public ResponseEntity<String> updateProject(@PathVariable Long projectId, @RequestBody ProjectDto projectDto) {
        ProjectEntity projectEntity = projectService.findById(projectId);
        Map<CriterionEntity, String> criterionMap = new HashMap<>();
        for(Map.Entry<Long, String> criterion: projectDto.criteria().entrySet()) {
            Optional<CriterionEntity> criterionEntity = criterionService.getCriterionById(criterion.getKey());
            if(criterionEntity.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            criterionMap.put(criterionEntity.get(), criterion.getValue());
        }
        projectEntity.setName(projectDto.name());
        projectEntity.setDescription(projectDto.description());
        projectEntity.setCriteria(criterionMap);
        if(projectService.createOrUpdate(projectEntity) == null){
            return new ResponseEntity<>("Указаное имя прооекта занято", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{projectName}")
    public ResponseEntity<String> deleteProject(@PathVariable String projectName) {
        if(!projectService.deleteByName(projectName)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{projectId}")
    public ResponseEntity<ProjectDetailsDto> getProject(@PathVariable Long projectId) {
        ProjectEntity projectEntity = projectService.findById(projectId);
        return new ResponseEntity<>(projectEntity.toDto(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectDetailsDto>> getAllProjects() {
        List<ProjectDetailsDto> projectEntities = projectService.findAll().stream().map(ProjectEntity::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(projectEntities, HttpStatus.OK);
    }
}
