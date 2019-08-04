package com.example.smartcard.service;

import com.example.smartcard.domain.Request;
import com.example.smartcard.repository.RequestRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    
    @Autowired
    private RequestRepository requestRepository;
    
    public Long addNewCardRequest(Request request) {
        Request r = requestRepository.save(request);
        return r.getId();
    }
    
    public List<Request> getNotManipulatedNewCardReq() {
        return requestRepository.findAllByManipulated(false);
    }
    
    public Request getRequestById(Long id) {
        if(requestRepository.findById(id).isPresent()) {
            return requestRepository.findById(id).get(); 
        }
        return null;
    }
    
    public int manipulate(Long id, boolean status) {
        return requestRepository.updateAllRequestSetApprovalForId(id, status);
    }
}
