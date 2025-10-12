package com.tom.User_Management.controller;

import com.tom.User_Management.model.User;
import com.tom.User_Management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller // TELLS SPRING THIS CLASS HANDLES WEB REQUESTS AND RETURNS HTML VIEWS
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/signup")
    public String SignupPage(Model model) {
        model.addAttribute("user", new User());
        return "signup"; // matches signup.html in /templates
    }



}
