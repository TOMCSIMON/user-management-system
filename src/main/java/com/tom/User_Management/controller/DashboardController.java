package com.tom.User_Management.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")

        public String showDashboard(Model model, Principal principal) {

            if(principal != null){
                model.addAttribute("username", principal.getName());
            }
            else{
                model.addAttribute("username", "Guest");
            }
           return "dashboard";
        }
    }


