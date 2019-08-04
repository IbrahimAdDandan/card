package com.example.smartcard.controller;

import com.example.smartcard.domain.Log;
import com.example.smartcard.domain.User;
import com.example.smartcard.service.LogService;
import com.example.smartcard.service.UserDetailsImp;
import com.example.smartcard.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService us;
    
    @Autowired
    private LogService logService;

    @RequestMapping("register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Register");
        return "register";
    }

    @RequestMapping("login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Login");
        return "login";
    }

    @RequestMapping(value = "users/save", method = RequestMethod.POST)
    public String save(User user) {
        user.setRole_name("CETIZEN");
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        us.add(user);
        return "redirect:/";
    }
    
    @RequestMapping(value = "/manage-users/create", method = RequestMethod.POST)
    public String createUser(User user, Authentication authentication) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        us.add(user);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String action = "Add a new user with role: " + user.getRole_name();
        Log log = new Log(username, action);
        logService.save(log);
        return "redirect:/manage-users/";
    }

    @RequestMapping("/manage-users/")
    public String manageUsers(Authentication authentication, Model model) {
        model.addAttribute("pageTitle", "Manage Users");
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        List<User> users = us.getUsers();
        model.addAttribute("users", users);
        return "users/manageUsers";
    }
    
    @RequestMapping("/manage-users/delete/{id}")
    public String delete(Model model, @PathVariable Long id, Authentication authentication) {
        us.remove(id);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String action = "Delete user with id: " + id;
        Log log = new Log(username, action);
        logService.save(log);
        return "redirect:/manage-users/";
    }
    
    @RequestMapping("/manage-users/create-form")
    public String createNewUser(Authentication authentication, Model model) {
        model.addAttribute("pageTitle", "Manage Users");
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        model.addAttribute("user", new User());
        return "users/createUser";
    }

}
