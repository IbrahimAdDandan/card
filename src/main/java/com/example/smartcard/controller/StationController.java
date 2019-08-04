package com.example.smartcard.controller;

import com.example.smartcard.domain.FillOperation;
import com.example.smartcard.domain.FraudOperation;
import com.example.smartcard.domain.Log;
import com.example.smartcard.domain.SmartCard;
import com.example.smartcard.domain.Station;
import com.example.smartcard.service.CardService;
import com.example.smartcard.service.FillOperationService;
import com.example.smartcard.service.FraudOperationService;
import com.example.smartcard.service.LogService;
import com.example.smartcard.service.StationService;
import com.example.smartcard.service.UserDetailsImp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StationController {

    @Autowired
    private StationService stationService;

    @Autowired
    private LogService logService;

    @Autowired
    private CardService cardService;

    @Autowired
    private FillOperationService fillOperationService;

    @Autowired
    private FraudOperationService fraudOperationService;

    @RequestMapping("/stations")
    public String stations(Authentication authentication, Model model) {
        model.addAttribute("pageTitle", "Stations");
        if (authentication != null) {
            UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Long userId = userDetails.getUserid();
            String userRole = userDetails.getAuthorities().toArray()[0].toString();
            model.addAttribute("userId", userId);
            model.addAttribute("username", username);
            model.addAttribute("role", userRole);
        }
        List<Station> stations = stationService.getOrderedStations();
        model.addAttribute("stations", stations);
        return "station/stations";
    }

    @RequestMapping("/manage-stations/new-station")
    public String newStation(Authentication authentication, Model model) {
        model.addAttribute("pageTitle", "New Station");
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        model.addAttribute("station", new Station());
        return "station/newStation";
    }

    @RequestMapping(value = "/manage-stations/save", method = RequestMethod.POST)
    public String save(Authentication authentication, Station station) {
        stationService.save(station);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String action = "Add new station with idintifier: " + station.getId();
        Log log = new Log(username, action);
        logService.save(log);
        return "redirect:/stations";
    }

    @RequestMapping("/station/{id}")
    public String station(Authentication authentication, Model model, @PathVariable Long id, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "success", required = false) String success) {
        model.addAttribute("pageTitle", "Station");
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        Station station = stationService.getById(id);
        model.addAttribute("station", station);
        if (error != null) {
            model.addAttribute("error", error);
        }
        if (success != null) {
            model.addAttribute("success", success);
        }

        return "station/station";
    }

    @RequestMapping("/station/queue-increment/{id}")
    public String queueIncrement(@PathVariable Long id) {
        stationService.addToQueue(id);
        return "redirect:/station/" + id;
    }

    @RequestMapping(value = "/station/fill-operation", method = RequestMethod.POST)
    public String fillOperation(Long stationId, Long cardId, double ammount, Authentication authentication) {
        // if station.ammount > ammout
        //      if card.status is valid and not expired
        //          if card.ammount > ammount
        //              fill;
        //          else return error
        //      else add to fraud log to the card
        // else add to fraud log to the station employee.
        // prefer transaction on 2 tables!!
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Station station = stationService.getById(stationId);
        SmartCard card = cardService.getById(cardId);
        String error = null;
        if (card != null && card.getStatus().equals("VALID")) {
            if (card.getRestAmmount() > ammount) {
                if (station.getAmmount() > ammount) {
                    Date now = new Date();
                    if (now.after(card.getExpireDate())) {
                        String type = "Expired Card";
                        FraudOperation fo = new FraudOperation(station, card, username, type);
                        fraudOperationService.saveOperation(fo);
                        error = type;
                    } else {
                        stationService.fillOperation(stationId, ammount);
                        cardService.fillOperation(cardId, ammount);
                        FillOperation fillOperation = new FillOperation(station, card, ammount);
                        fillOperationService.save(fillOperation);
                    }
                } else {
                    String type = "Not enough ammount in the Sation";
                    FraudOperation fo = new FraudOperation(station, card, username, type);
                    fraudOperationService.saveOperation(fo);
                    error = type;
                }
            } else {
                String type = "Not enough ammount in the Card";
                FraudOperation fo = new FraudOperation(station, card, username, type);
                fraudOperationService.saveOperation(fo);
                error = type;
            }

        } else {
            String type = "Invalid Card";
            FraudOperation fo = new FraudOperation(station, card, username, type);
            fraudOperationService.saveOperation(fo);
            error = type;
        }
        if (error != null) {
            return "redirect:/station/" + stationId + "?error=" + error + "#fill-operation-form";
        }
        return "redirect:/station/" + stationId + "?success=operation completed successfuly#fill-operation-form";
    }

    @RequestMapping("/closest-station")
    public String closestStations(Authentication authentication, Model model) {
        model.addAttribute("pageTitle", "Closest Station");
        if (authentication != null) {
            UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Long userId = userDetails.getUserid();
            String userRole = userDetails.getAuthorities().toArray()[0].toString();
            model.addAttribute("userId", userId);
            model.addAttribute("username", username);
            model.addAttribute("role", userRole);
        }
        return "station/closestStations";
    }

    @RequestMapping("/dashboard/statistics")
    public String statis(Authentication authentication, Model model) {
        model.addAttribute("pageTitle", "Statistics");
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        return "station/statistics";
    }
}
