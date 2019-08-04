package com.example.smartcard.controller;

import com.example.smartcard.domain.Log;
import com.example.smartcard.domain.Request;
import com.example.smartcard.domain.SmartCard;
import com.example.smartcard.service.CardService;
import com.example.smartcard.service.LogService;
import com.example.smartcard.service.RequestService;
import com.example.smartcard.service.UserDetailsImp;
import java.time.LocalDate;
import java.util.Calendar;
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
public class RequestController {

    @Autowired
    private RequestService requestService;
    
    @Autowired
    private CardService cardService;

    @Autowired
    private LogService logService;

    @RequestMapping("new-card")
    public String newCard(Authentication authentication, Model model) {
        model.addAttribute("pageTitle", "New Card Request");
        model.addAttribute("request", new Request());
        if (authentication != null) {
            UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Long userId = userDetails.getUserid();
            String userRole = userDetails.getAuthorities().toArray()[0].toString();
            model.addAttribute("userId", userId);
            model.addAttribute("username", username);
            model.addAttribute("role", userRole);
        }
        return "card/newCardForm";
    }

    @RequestMapping(value = "create-new-card-req", method = RequestMethod.POST)
    public String createNewCardRequest(Authentication authentication, Model model, Request request) {
        Long id = requestService.addNewCardRequest(request);
        model.addAttribute("requestId", id);
        model.addAttribute("pageTitle", "New Card Request");
        if (authentication != null) {
            UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Long userId = userDetails.getUserid();
            String userRole = userDetails.getAuthorities().toArray()[0].toString();
            model.addAttribute("userId", userId);
            model.addAttribute("username", username);
            model.addAttribute("role", userRole);
        }
        return "card/newCardSuccess";
    }

    @RequestMapping("search-request")
    public String searchRequest(Authentication authentication, Model model) {
        model.addAttribute("pageTitle", "Search for Request");
        if (authentication != null) {
            UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Long userId = userDetails.getUserid();
            String userRole = userDetails.getAuthorities().toArray()[0].toString();
            model.addAttribute("userId", userId);
            model.addAttribute("username", username);
            model.addAttribute("role", userRole);
        }
        return "card/searchRequest";
    }

    @RequestMapping(value = "search-new-card-req", method = RequestMethod.GET)
    public String searching(Authentication authentication, Model model, @RequestParam("keywords") String keywords) {
        model.addAttribute("pageTitle", "Search for Request");
        Request request = requestService.getRequestById(Long.parseLong(keywords));
        model.addAttribute("result", "result");
        model.addAttribute("request", request);
        if (authentication != null) {
            UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Long userId = userDetails.getUserid();
            String userRole = userDetails.getAuthorities().toArray()[0].toString();
            model.addAttribute("userId", userId);
            model.addAttribute("username", username);
            model.addAttribute("role", userRole);
        }
        return "card/searchRequest";
    }

    @RequestMapping("/dashboard/manage-new-card")
    public String manageNewCard(Authentication authentication, Model model) {
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        model.addAttribute("pageTitle", "Manage New Card Requests");
        List<Request> requests = requestService.getNotManipulatedNewCardReq();
        model.addAttribute("requests", requests);
        return "card/manageNewRequest";
    }

    @RequestMapping("/dashboard/manage-new-card/approve/{id}")
    public String approve(@PathVariable Long id, Authentication authentication) {
        requestService.manipulate(id, true);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String action = "Approve new card request with idintifier: " + id;
        Log log = new Log(username, action);
        logService.save(log);
        Request req = requestService.getRequestById(id);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 3);
        Date date = cal.getTime();
        SmartCard card = new SmartCard(req.getFull_name(), req.getAddress(), req.getNationalId(), req.getPhone(), date, 225, 225);
        cardService.save(card);
        return "redirect:/dashboard/manage-new-card";
    }

    @RequestMapping("/dashboard/manage-new-card/reject/{id}")
    public String reject(@PathVariable Long id, Authentication authentication) {
        requestService.manipulate(id, false);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String action = "Reject new card request with idintifier: " + id;
        Log log = new Log(username, action);
        logService.save(log);
        return "redirect:/dashboard/manage-new-card";
    }
}
