package com.example.smartcard.service;

import com.example.smartcard.domain.StopRequest;
import com.example.smartcard.repository.StopRequestRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StopRequestService {
    @Autowired
    private StopRequestRepository stopRequestRepository;
    
    public Long addStopCardRequest(StopRequest request) {
        StopRequest r = stopRequestRepository.save(request);
        return r.getId();
    }
    
    public List<StopRequest> getNotManipulatedStopCardReq() {
        return stopRequestRepository.findAllByManipulated(false);
    }
    
    public StopRequest getRequestById(Long id) {
        if(stopRequestRepository.findById(id).isPresent()) {
            return stopRequestRepository.findById(id).get(); 
        }
        return null;
    }
    
    public int manipulate(Long id, boolean status) {
        return stopRequestRepository.updateAllStopRequestSetApprovalForId(id, status);
    }
}
