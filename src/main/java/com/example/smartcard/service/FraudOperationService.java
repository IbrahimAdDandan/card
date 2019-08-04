package com.example.smartcard.service;

import com.example.smartcard.domain.FraudOperation;
import com.example.smartcard.repository.FraudOperationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FraudOperationService {
    @Autowired
    FraudOperationRepository fraudOperationRepository;
    
    public List<FraudOperation> getFraudOperations() {
        return (List<FraudOperation>) fraudOperationRepository.findAll();
    }
    
    public void saveOperation(FraudOperation fraud) {
        fraudOperationRepository.save(fraud);
    }
}
