package com.example.smartcard.service;

import com.example.smartcard.domain.FillOperation;
import com.example.smartcard.repository.FillOperationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FillOperationService {
    @Autowired
    FillOperationRepository fillOperationRepository;
    
    public void save(FillOperation fillOperation) {
        fillOperationRepository.save(fillOperation);
    }
    
    public List<FillOperation> getAll() {
        return (List<FillOperation>) fillOperationRepository.findAll();
    }
    
    public List<Object[]> getStatis() {
        return fillOperationRepository.statistics();
    }
}
