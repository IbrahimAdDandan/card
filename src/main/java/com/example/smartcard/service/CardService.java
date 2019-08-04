package com.example.smartcard.service;

import com.example.smartcard.domain.SmartCard;
import com.example.smartcard.repository.CardRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    
    public void save(SmartCard card) {
        cardRepository.save(card);
    }
    
    public List<SmartCard> getByNationalId(Long nationalId) {
        return cardRepository.findAllByNationalId(nationalId);
    }
    
    public void fillOperation(Long id, double ammount) {
        cardRepository.fillOperation(id, ammount);
    }
    
    public void refillCard(Long id, double ammount) {
        cardRepository.refillOperation(id, ammount);
    }

    public SmartCard getById(Long cardId) {
        if (cardRepository.findById(cardId).isPresent()) {
            return cardRepository.findById(cardId).get();
        }
        return null;
    }

    public List<SmartCard> getAll() {
        return (List<SmartCard>) cardRepository.findAll();
    }
    
    public void activate(Long id) {
        cardRepository.activate(id);
    }
    
    public void deactivate(Long id) {
        cardRepository.deactivate(id);
    }
}
