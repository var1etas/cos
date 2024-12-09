package ru.skb.cos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skb.cos.controllers.dto.CriterionDto;
import ru.skb.cos.model.entity.CriterionEntity;
import ru.skb.cos.model.services.CriterionService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/criterion")
public class CriterionController {

    CriterionService criterionService;

    @Autowired
    public CriterionController(CriterionService criterionService) {
        this.criterionService = criterionService;
    }

    @GetMapping("{id}")
    public ResponseEntity<CriterionEntity> getCriterion(@PathVariable Long id) {
        Optional<CriterionEntity> criterion = criterionService.getCriterionById(id);
        if (criterion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(criterion.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCriterion(@RequestBody CriterionDto criterionDto) {
        CriterionEntity criterion = new CriterionEntity(criterionDto.name(), criterionDto.description());
        if(!criterionService.createCriterion(criterion)) {
            return new ResponseEntity<>("Criterion already exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCriterion(@PathVariable Long id ,@RequestBody CriterionDto criterionDto) {
        CriterionEntity criterion = new CriterionEntity(criterionDto.name(), criterionDto.description());
        if(!criterionService.updateCriterion(id, criterion)) {
            return new ResponseEntity<>("Criterion to update not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCriterion(@PathVariable Long id) {
        if(!criterionService.deleteCriterion(id)) {
            return new ResponseEntity<>("Criterion to delete not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CriterionEntity>> getAllCriteria() {
        List<CriterionEntity> criterionList = criterionService.getAllCriteria();
        return new ResponseEntity<>(criterionList, HttpStatus.OK);
    }
}
