package ru.skb.cos.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skb.cos.model.entity.CriterionEntity;
import ru.skb.cos.repository.CriterionRepository;

import java.util.Optional;

@Service
public class CriterionService {
    private final CriterionRepository criterionRepository;

    @Autowired
    public CriterionService(CriterionRepository criterionRepository) {
        this.criterionRepository = criterionRepository;
    }

    public boolean createCriterion(CriterionEntity criterion) {
        Optional<CriterionEntity> existCheck = criterionRepository.findById(criterion.getId());
        if (existCheck.isPresent()) {
            return false;
        }
        criterionRepository.save(criterion);
        return true;
    }

    public CriterionEntity getCriterionByName(String name) {
        return criterionRepository.findByName(name);
    }

    public Optional<CriterionEntity> getCriterionById(Long id) {
        return criterionRepository.findById(id);
    }

    public boolean updateCriterion(Long id, CriterionEntity criterion) {
        Optional<CriterionEntity> criterionEntity = criterionRepository.findById(id);
        if (criterionEntity.isEmpty()) {
            return false;
        }
        criterionEntity.get().setName(criterion.getName());
        criterionEntity.get().setDescription(criterion.getDescription());

        criterionRepository.save(criterionEntity.get());
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
}
