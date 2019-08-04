package com.example.smartcard.service;

import com.example.smartcard.domain.Station;
import com.example.smartcard.repository.StationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService {
    @Autowired
    StationRepository stationRepository;
    
    public List<Station> getStations() {
        return (List<Station>) stationRepository.findAll();
    }
    
    public List<Station> getOrderedStations() {
        return (List<Station>) stationRepository.orderedStations();
    }
    
//    public List<Station> getOrderedStations(String address) {
//        return (List<Station>) stationRepository.findAllOrderByQueueCountOrderByAmmount();
//    }
    
    public void addToQueue(Long id) {
        stationRepository.addToQueueCount(id);
    }
    
    public void fillOperation(Long id, double ammount) {
        stationRepository.fillOperation(id, ammount);
    }
    
    public void refillStation(Long id, double ammount) {
        stationRepository.refillOperation(id, ammount);
    }
    
    public void save(Station station) {
        stationRepository.save(station);
    }

    public Station getById(Long id) {
        if(stationRepository.findById(id).isPresent()) {
            return stationRepository.findById(id).get();
        }
        return null;
    }
}
