package com.example.smartcard.controller;

import com.example.smartcard.domain.FraudOperation;
import com.example.smartcard.service.FraudOperationService;
import com.example.smartcard.service.UserDetailsImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FraudOperationController {
    
    @Autowired
    private FraudOperationService fraudOperationService;

    @RequestMapping("/dashboard/fraud")
    public String frauds(Authentication authentication, Model model) {
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        List<FraudOperation> frauds = fraudOperationService.getFraudOperations();
        model.addAttribute("frauds", frauds);
        return "fraud";
    }
}
