package com.example.smartcard.repository;

import com.example.smartcard.domain.FraudOperation;
import org.springframework.data.repository.CrudRepository;

public interface FraudOperationRepository extends CrudRepository<FraudOperation, Long> {
    
}
