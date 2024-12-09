package ru.skb.cos.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skb.cos.model.entity.TemplateEntity;
import ru.skb.cos.repository.CriterionRepository;
import ru.skb.cos.repository.TemplateRepository;

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

    public boolean createTemplate(TemplateEntity templateEntity) {
        Optional<TemplateEntity> loadedTemplate = templateRepository.findByName(templateEntity.getName());
        if (loadedTemplate.isPresent()) {
            return false;
        }
        templateRepository.save(templateEntity);
        return true;
    }

    public Optional<TemplateEntity> getTemplate(Long id) {
        return templateRepository.findById(id);
    }

    public boolean updateTemplate(Long id, TemplateEntity templateEntity) {
        Optional<TemplateEntity> loadedTemplate = templateRepository.findById(id);
        if (loadedTemplate.isEmpty()) {
            return false;
        }
        loadedTemplate.get().setName(templateEntity.getName());
        loadedTemplate.get().setDescription(templateEntity.getDescription());
        loadedTemplate.get().setCriteria(templateEntity.getCriteria());
        templateRepository.save(loadedTemplate.get());
        return true;
    }

    public boolean deleteTemplate(Long id) {
        Optional<TemplateEntity> loadedTemplate = templateRepository.findById(id);
        if (loadedTemplate.isEmpty()) {
            return false;
        }
        templateRepository.delete(loadedTemplate.get());
        return true;
    }


}
