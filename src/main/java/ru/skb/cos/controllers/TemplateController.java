package ru.skb.cos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skb.cos.controllers.dto.TemplateDto;
import ru.skb.cos.model.entity.CriterionEntity;
import ru.skb.cos.model.entity.TemplateEntity;
import ru.skb.cos.model.services.CriterionService;
import ru.skb.cos.model.services.TemplateService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/template")
public class TemplateController {

    TemplateService templateService;
    CriterionService criterionService;

    @Autowired
    public TemplateController(TemplateService templateService, CriterionService criterionService) {
        this.templateService = templateService;
        this.criterionService = criterionService;
    }

    @PostMapping
    public ResponseEntity<String> addTemplate(@RequestBody TemplateDto templateDto) {
        List<CriterionEntity> criterionEntityList = criterionService.getAllCriteriaById(templateDto.criteriaIdList());
        TemplateEntity template = new TemplateEntity(templateDto.name(), templateDto.description(), criterionEntityList);
        if(!templateService.createTemplate(template)) {
            return new ResponseEntity<>("Template already exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TemplateEntity> getTemplate(@PathVariable Long id) {
        Optional<TemplateEntity> templateEntity = templateService.getTemplate(id);
        if(templateEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(templateEntity.get(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateTemplate(@PathVariable Long id, @RequestBody TemplateDto templateDto) {
        List<CriterionEntity> criterionEntityList = criterionService.getAllCriteriaById(templateDto.criteriaIdList());
        TemplateEntity template = new TemplateEntity(templateDto.name(), templateDto.description(), criterionEntityList);
        if(!templateService.updateTemplate(id, template)) {
            return new ResponseEntity<>("Template to update not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTemplate(@PathVariable Long id) {
        if(!templateService.deleteTemplate(id)) {
            return new ResponseEntity<>("Template to delete not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
