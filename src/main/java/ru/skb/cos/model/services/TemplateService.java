package ru.skb.cos.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skb.cos.model.entity.TemplateEntity;
import ru.skb.cos.repository.TemplateRepository;

import java.util.Optional;

@Service
public class TemplateService {
    TemplateRepository templateRepository;

    @Autowired
    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public boolean createTemplate(TemplateEntity template) {
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

    public boolean updateTemplate(TemplateEntity template) {
        Optional<TemplateEntity> existCheck = templateRepository.findById(template.getId());
        if (existCheck.isEmpty()) {
            return false;
        }
        existCheck.get().setName(template.getName());
        existCheck.get().setDescription(template.getDescription());
        existCheck.get().setCriteria(template.getCriteria());
        templateRepository.save(existCheck.get());
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
