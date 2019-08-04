package com.example.smartcard.service;

import com.example.smartcard.domain.Log;
import com.example.smartcard.repository.LogRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    
    @Autowired
    LogRepository logRepository;
    
    public List<Log> getAll() {
        return (List<Log>) logRepository.findAll();
    }
    
    public void save(Log log) {
        logRepository.save(log);
    }
    
}
