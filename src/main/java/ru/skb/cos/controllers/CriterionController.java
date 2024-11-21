package ru.skb.cos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skb.cos.controllers.dto.CriterionRequestDto;
import ru.skb.cos.model.entity.CriterionEntity;
import ru.skb.cos.model.services.CriterionService;

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
    public ResponseEntity<String> getCriterion(@PathVariable Long id) {
        Optional<CriterionEntity> criterion = criterionService.getCriterionById(id);
        if (criterion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(criterion.get().toString(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCriterion(@RequestBody CriterionRequestDto criterionDto) {
        if(!criterionService.createCriterion(criterionDto)) {
            return new ResponseEntity<>("Criterion already exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> deleteCriterion(@PathVariable Long id ,@RequestBody CriterionRequestDto criterionDto) {
        if(!criterionService.updateCriterion(id, criterionDto)) {
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
}
