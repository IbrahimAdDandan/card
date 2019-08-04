package com.example.smartcard.service;

import com.example.smartcard.domain.Complaint;
import com.example.smartcard.repository.ComplaintRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {
    @Autowired
    ComplaintRepository complaintRepository;
    
    public List<Complaint> getAll() {
        return (List<Complaint>) complaintRepository.findAll();
    }
    
    public List<Complaint> getNotManipulated() {
        return (List<Complaint>) complaintRepository.findAllByManipulatedIsFalse();
    }
    
    public Long saveComplaint(Complaint complaint) {
        return complaintRepository.save(complaint).getId();
    }

    public Complaint getById(Long id) {
        if(complaintRepository.findById(id).isPresent()) {
            return complaintRepository.findById(id).get();
        }
        return null;
    }
}
