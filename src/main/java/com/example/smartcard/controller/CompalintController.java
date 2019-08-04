package com.example.smartcard.controller;

import com.example.smartcard.domain.Complaint;
import com.example.smartcard.domain.Log;
import com.example.smartcard.service.ComplaintService;
import com.example.smartcard.service.LogService;
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
public class CompalintController {

    @Autowired
    private ComplaintService complaintService;
    
    @Autowired
    private LogService logService;

    @RequestMapping("complaint-form")
    public String complaintForm(Model model, Authentication authentication) {
        model.addAttribute("complaint", new Complaint());
        model.addAttribute("pageTitle", "Complaint");
        if (authentication != null) {
            UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Long userId = userDetails.getUserid();
            String userRole = userDetails.getAuthorities().toArray()[0].toString();
            model.addAttribute("userId", userId);
            model.addAttribute("username", username);
            model.addAttribute("role", userRole);
        }
        return "complaint/complaint";
    }

    @RequestMapping(value = "compalint/save", method = RequestMethod.POST)
    public String saveComplaint(Model model, Complaint complaint) {
        complaintService.saveComplaint(complaint);
        model.addAttribute("pageTitle", "Complaint Success");
        return "complaint/complaintSuccess";
    }

    @RequestMapping("/dashboard/manage-complaints")
    public String manageComplaints(Model model, Authentication authentication) {
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        model.addAttribute("pageTitle", "Manage Complaints");
        List<Complaint> complaints = complaintService.getNotManipulated();
        model.addAttribute("complaints", complaints);
        return "complaint/manageComplaints";
    }

    @RequestMapping(value = "/dashboard/manipulate-complaint/{id}", method = RequestMethod.POST)
    public String manipulateComplaint(String notice, @PathVariable Long id, Authentication authentication) {
        Complaint complaint = complaintService.getById(id);
        if (complaint != null) {
            complaint.setManipulated(true);
            complaint.setNotice(notice);
            complaintService.saveComplaint(complaint);
            UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
            String username = userDetails.getUsername();
            String action = "manipulate complaint with idintifier: " + id;
            Log log = new Log(username, action);
            logService.save(log);
        }
        return "redirect:/dashboard/manage-complaints";
    }

}
