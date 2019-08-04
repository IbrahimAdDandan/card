package com.example.smartcard.service;

import com.example.smartcard.domain.FAQ;
import com.example.smartcard.repository.FAQRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FAQService {
    @Autowired
    FAQRepository fAQRepository;
    
    public List<FAQ> getAll(){
        return (List<FAQ>) fAQRepository.findAll();
    }
    
    public Long saveFAQ(FAQ faq) {
        return fAQRepository.save(faq).getId();
    }

    public void remove(Long id) {
        fAQRepository.deleteById(id);
    }

    public FAQ getByID(Long id) {
        if(fAQRepository.findById(id).isPresent()) {
            return fAQRepository.findById(id).get();
        }
        return null;
    }
}
