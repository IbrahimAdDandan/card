package com.example.smartcard.controller;


import com.example.smartcard.service.UserDetailsImp;
import com.example.smartcard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService us;

    @RequestMapping(value = "/")
    public String list(Authentication authentication, Model model) {
        model.addAttribute("pageTitle", "Smart Card -Home");
        if (authentication != null) {
            UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
            String username = userDetails.getUsername();
            Long userId = userDetails.getUserid();
            String userRole = userDetails.getAuthorities().toArray()[0].toString();
            model.addAttribute("userId", userId);
            model.addAttribute("username", username);
            model.addAttribute("role", userRole);
        }
        return "home";
    }
}
