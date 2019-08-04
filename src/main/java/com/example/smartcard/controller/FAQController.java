package com.example.smartcard.controller;

import com.example.smartcard.domain.FAQ;
import com.example.smartcard.service.FAQService;
import com.example.smartcard.service.UserDetailsImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FAQController {

    @Autowired
    FAQService fAQService;

    @RequestMapping("faq")
    public String faq(Model model, Authentication authentication) {
        List<FAQ> faqs = fAQService.getAll();
        model.addAttribute("faqs", faqs);
        model.addAttribute("pageTitle", "FAQ");
        if (authentication != null) {
            UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Long userId = userDetails.getUserid();
            String userRole = userDetails.getAuthorities().toArray()[0].toString();
            model.addAttribute("userId", userId);
            model.addAttribute("username", username);
            model.addAttribute("role", userRole);
        }
        return "faq/faq";
    }

    @RequestMapping("/dashboard/manage-faq")
    public String manageFaq(Model model, Authentication authentication) {
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        List<FAQ> faqs = fAQService.getAll();
        model.addAttribute("faqs", faqs);
        model.addAttribute("pageTitle", "Manage FAQ");
        return "faq/ManageFAQ";
    }
    
    @RequestMapping("/dashboard/new-faq")
    public String newFaq(Model model, Authentication authentication) {
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        model.addAttribute("faq", new FAQ());
        model.addAttribute("pageTitle", "New FAQ");
        return "faq/NewFAQ";
    }
    
    @RequestMapping(value = "/dashboard/save-faq", method = RequestMethod.POST)
    public String saveFAQ(FAQ faq) {
        fAQService.saveFAQ(faq);
        return "redirect:/dashboard/manage-faq";
    }
    
    @RequestMapping("/dashboard/manage-faq/delete/{id}")
    public String delete(@PathVariable Long id) {
        fAQService.remove(id);
        return "redirect:/dashboard/manage-faq";
    }
    
    @RequestMapping("/dashboard/manage-faq/edit/{id}")
    public String edit(Model model, Authentication authentication, @PathVariable Long id) {
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        FAQ faq = fAQService.getByID(id);
        model.addAttribute("faq", faq);
        model.addAttribute("pageTitle", "Edit FAQ");
        return "faq/editFaq";
    }
}
