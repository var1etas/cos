package ru.skb.cos.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skb.cos.controllers.dto.CriterionDto;
import ru.skb.cos.model.entity.CriterionEntity;
import ru.skb.cos.repository.CriterionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CriterionService {
    private final CriterionRepository criterionRepository;

    @Autowired
    public CriterionService(CriterionRepository criterionRepository) {
        this.criterionRepository = criterionRepository;
    }

    public boolean createCriterion(CriterionEntity criterionEntity) {
        Optional<CriterionEntity> loadedCriterion = criterionRepository.findByName(criterionEntity.getName());
        if (loadedCriterion.isPresent()) {
            return false;
        }
        criterionRepository.save(criterionEntity);
        return true;
    }

    public CriterionEntity getCriterionByName(String name) {
        return criterionRepository.findByName(name).get();
    }

    public Optional<CriterionEntity> getCriterionById(Long id) {
        return criterionRepository.findById(id);
    }

    public boolean updateCriterion(Long id, CriterionEntity criterionEntity) {
        Optional<CriterionEntity> loadedCriterion = criterionRepository.findById(id);
        if (loadedCriterion.isEmpty()) {
            return false;
        }
        loadedCriterion.get().setName(criterionEntity.getName());
        loadedCriterion.get().setDescription(criterionEntity.getDescription());

        criterionRepository.save(loadedCriterion.get());
        return true;
    }

    public boolean deleteCriterion(Long id) {
        Optional<CriterionEntity> criterionEntity = criterionRepository.findById(id);
        if (criterionEntity.isEmpty()) {
            return false;
        }
        criterionRepository.delete(criterionEntity.get());
        return true;
    }

    public List<CriterionEntity> getAllCriteriaById(List<Long> idList) {
        List<CriterionEntity> criterionEntityList = new ArrayList<>();
        for(Long id : idList) {
            Optional<CriterionEntity> criterionEntity = getCriterionById(id);
            if(criterionEntity.isEmpty()) {
                return null;
            }
            criterionEntityList.add(criterionEntity.get());
        }
        return criterionEntityList;
    }

    public List<CriterionEntity> getAllCriteria() {
        List<CriterionEntity> criterionEntityList = new ArrayList<>();
        criterionRepository.findAll().forEach(criterionEntityList::add);
        return criterionEntityList;
    }
}
