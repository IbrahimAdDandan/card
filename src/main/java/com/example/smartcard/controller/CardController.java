package com.example.smartcard.controller;

import com.example.smartcard.domain.Log;
import com.example.smartcard.domain.SmartCard;
import com.example.smartcard.service.CardService;
import com.example.smartcard.service.LogService;
import com.example.smartcard.service.UserDetailsImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CardController {
    @Autowired
    private CardService cardService;
    
    @Autowired
    private LogService logService;
    
    @RequestMapping("/my-cards/")
    public String myCards(Authentication authentication, Model model) {
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        model.addAttribute("pageTitle", "My Cards");
        Long nationalId = userDetails.getNationalId();
        List<SmartCard> myCards = cardService.getByNationalId(nationalId);
        model.addAttribute("myCards", myCards);
        return "card/myCards";
    }
    
    @RequestMapping("/my-cards/{id}")
    public String myCard(Authentication authentication, Model model, @PathVariable Long id) {
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        model.addAttribute("pageTitle", "My Card");
        SmartCard myCard = cardService.getById(id);
        model.addAttribute("myCard", myCard);
        return "card/myCard";
    }
    
    @RequestMapping("/dashboard/cards")
    public String cards(Authentication authentication, Model model) {
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        model.addAttribute("pageTitle", "Manage Cards");
        List<SmartCard> cards = cardService.getAll();
        model.addAttribute("cards", cards);
        return "card/cards";
    }
    
    @RequestMapping("/dashboard/cards/activate/{id}")
    public String activateCard(Authentication authentication, @PathVariable Long id) {
        cardService.activate(id);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String action = "Activated smart card with iditifier: " + id;
        Log log = new Log(username, action);
        logService.save(log);
        return "redirect:/dashboard/cards";
    }
    
    @RequestMapping("/dashboard/cards/deactivate/{id}")
    public String deactivateCard(Authentication authentication, @PathVariable Long id) {
        cardService.deactivate(id);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String action = "Deactivated smart card with iditifier: " + id;
        Log log = new Log(username, action);
        logService.save(log);
        return "redirect:/dashboard/cards";
    }
    
}
