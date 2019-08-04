package com.example.smartcard.controller;

import com.example.smartcard.domain.Log;
import com.example.smartcard.service.LogService;
import com.example.smartcard.service.UserDetailsImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogController {

    @Autowired
    LogService logService;

    @RequestMapping("/log")
    public String log(Authentication authentication, Model model) {
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        model.addAttribute("pageTitle", "Actions Log");
        List<Log> actionLog = logService.getAll();
        model.addAttribute("actionslog", actionLog);
        return "log";
    }
}
