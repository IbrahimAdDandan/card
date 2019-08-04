package com.example.smartcard.controller;

import com.example.smartcard.domain.Station;
import com.example.smartcard.domain.StationStat;
import com.example.smartcard.service.FillOperationService;
import com.example.smartcard.service.StationService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationAPIController {
    @Autowired
    private StationService stationService;
    
    @Autowired
    private FillOperationService fillOperationService;
    
    @RequestMapping(value = "/get-sations", method = RequestMethod.GET)
    public List<Station> getStations() {
        List<Station> stations = stationService.getStations();
        for (Station station : stations) {
            station.setFillOperations(null);
            station.setFraudOperations(null);
        }
        return stations;
    }
    
    @RequestMapping(value = "/dashboard/get-statistics/station", method = RequestMethod.GET)
    public List<StationStat> stationStatis() {
        List<Station> stations = stationService.getStations();
        List<StationStat> stationStats = new LinkedList<>();
        StationStat ss = new StationStat();
        for (Station station : stations) {
            ss.setAddress(station.getAddress());
            ss.setName(station.getName());
            ss.setId(station.getId());
            ss.setFillOpsNum(station.getFillOperations().size());
            ss.setFraudOpsNum(station.getFraudOperations().size());
            stationStats.add(ss.clone());
        }
        return stationStats;
    }
    
    @RequestMapping(value = "/dashboard/get-statistics/fill-ops", method = RequestMethod.GET)
    public List<Object[]> fillStatis() {
        return fillOperationService.getStatis();
    }
}
