package com.example.smartcard.controller;

import com.example.smartcard.domain.Log;
import com.example.smartcard.domain.StopRequest;
import com.example.smartcard.service.LogService;
import com.example.smartcard.service.StopRequestService;
import com.example.smartcard.service.UserDetailsImp;
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
public class StopRequestController {

    @Autowired
    StopRequestService stopRequestService;
    
    @Autowired
    private LogService logService;

    @RequestMapping("stop-card")
    public String stopCard(Authentication authentication, Model model) {
        model.addAttribute("pageTitle", "Stop Card Request");
        model.addAttribute("request", new StopRequest());
        if (authentication != null) {
            UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Long userId = userDetails.getUserid();
            String userRole = userDetails.getAuthorities().toArray()[0].toString();
            model.addAttribute("userId", userId);
            model.addAttribute("username", username);
            model.addAttribute("role", userRole);
        }
        return "card/stopCardForm";
    }

    @RequestMapping(value = "create-stop-card-req", method = RequestMethod.POST)
    public String createStopCardRequest(Authentication authentication, Model model, StopRequest request) {
        Long id = stopRequestService.addStopCardRequest(request);
        model.addAttribute("requestId", id);
        model.addAttribute("pageTitle", "Stop Card Request");
        if (authentication != null) {
            UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Long userId = userDetails.getUserid();
            String userRole = userDetails.getAuthorities().toArray()[0].toString();
            model.addAttribute("userId", userId);
            model.addAttribute("username", username);
            model.addAttribute("role", userRole);
        }
        return "card/stopCardSuccess";
    }

    @RequestMapping("search-stop-request")
    public String searchRequest(Authentication authentication, Model model) {
        model.addAttribute("pageTitle", "Search for stop Request");
        if (authentication != null) {
            UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Long userId = userDetails.getUserid();
            String userRole = userDetails.getAuthorities().toArray()[0].toString();
            model.addAttribute("userId", userId);
            model.addAttribute("username", username);
            model.addAttribute("role", userRole);
        }
        return "card/searchStopRequest";
    }

    @RequestMapping(value = "search-stop-card-req", method = RequestMethod.GET)
    public String searching(Authentication authentication, Model model, @RequestParam("keywords") String keywords) {
        model.addAttribute("pageTitle", "Search for Request");
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        StopRequest request = stopRequestService.getRequestById(Long.parseLong(keywords));
        model.addAttribute("result", "result");
        model.addAttribute("request", request);
        return "card/searchStopRequest";
    }

    @RequestMapping("/dashboard/manage-stop-card")
    public String manageStopCard(Authentication authentication, Model model) {
        model.addAttribute("pageTitle", "Search for Request");
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        List<StopRequest> requests = stopRequestService.getNotManipulatedStopCardReq();
        model.addAttribute("requests", requests);
        return "card/manageStopRequest";
    }
    
    @RequestMapping("/dashboard/manage-stop-card/approve/{id}")
    public String approve(@PathVariable Long id, Authentication authentication) {
        stopRequestService.manipulate(id, true);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String action = "Approve stop card request with idintifier: " + id;
        Log log = new Log(username, action);
        logService.save(log);
        return "redirect:/dashboard/manage-stop-card";
    }
    
    @RequestMapping("/dashboard/manage-stop-card/reject/{id}")
    public String reject(@PathVariable Long id, Authentication authentication) {
        stopRequestService.manipulate(id, false);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String action = "Reject stop card request with idintifier: " + id;
        Log log = new Log(username, action);
        logService.save(log);
        return "redirect:/dashboard/manage-stop-card";
    }
}
