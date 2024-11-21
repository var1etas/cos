package ru.skb.cos.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skb.cos.controllers.dto.TemplateRequestDto;
import ru.skb.cos.model.entity.CriterionEntity;
import ru.skb.cos.model.entity.TemplateEntity;
import ru.skb.cos.repository.CriterionRepository;
import ru.skb.cos.repository.TemplateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TemplateService {
    TemplateRepository templateRepository;
    CriterionRepository criterionRepository;

    @Autowired
    public TemplateService(TemplateRepository templateRepository, CriterionRepository criterionRepository) {
        this.templateRepository = templateRepository;
        this.criterionRepository = criterionRepository;
    }

    public boolean createTemplate(TemplateRequestDto templateDto) {
        List<CriterionEntity> criteriaList = new ArrayList<>();
        for(Long id: templateDto.criteriaIdList()){
            if(criterionRepository.findById(id).isEmpty()){
                throw new NoSuchElementException("The specified criterion was not found");
            }
            criteriaList.add(criterionRepository.findById(id).get());
        }
        TemplateEntity template = new TemplateEntity(templateDto.name(), templateDto.description(), criteriaList);
        Optional<TemplateEntity> existCheck = templateRepository.findById(template.getId());
        if (existCheck.isPresent()) {
            return false;
        }
        templateRepository.save(template);
        return true;
    }

    public Optional<TemplateEntity> getTemplate(Long id) {
        return templateRepository.findById(id);
    }

    public boolean updateTemplate(Long id, TemplateRequestDto templateDto) {
        List<CriterionEntity> criteriaList = new ArrayList<>();
        for(Long criterionId: templateDto.criteriaIdList()){
            if(criterionRepository.findById(criterionId).isEmpty()){
                throw new NoSuchElementException("The specified criterion was not found");
            }
            criteriaList.add(criterionRepository.findById(criterionId).get());
        }
        Optional<TemplateEntity> loadedTemplate = templateRepository.findById(id);
        if (loadedTemplate.isEmpty()) {
            return false;
        }
        loadedTemplate.get().setName(templateDto.name());
        loadedTemplate.get().setDescription(templateDto.description());
        loadedTemplate.get().setCriteria(criteriaList);
        templateRepository.save(loadedTemplate.get());
        return true;
    }

    public boolean deleteTemplate(Long id) {
        Optional<TemplateEntity> existCheck = templateRepository.findById(id);
        if (existCheck.isEmpty()) {
            return false;
        }
        templateRepository.delete(existCheck.get());
        return true;
    }


}
