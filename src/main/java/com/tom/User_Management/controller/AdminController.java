package com.tom.User_Management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;



@Controller
public class AdminController {


    // READ IN CRUD
    @GetMapping("/admin-login")
    public String showAdminLoginPage(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        else {
            model.addAttribute("username", "Guest");
        }

        return "admin-login";
    }


    // READ IN CRUD
    @GetMapping("/admin-dashboard")
    public String showAdminDashboard(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        else {
            model.addAttribute("username", "Guest");
        }

        return "admin-dashboard";
    }




    // CREATE IN CRUD



    // UPDATE IN CRUD



    // DELETE IN CRUD


}
