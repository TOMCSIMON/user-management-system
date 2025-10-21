package com.tom.User_Management.controller;

import com.tom.User_Management.model.User;
import com.tom.User_Management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller // TELLS SPRING THIS CLASS HANDLES WEB REQUESTS AND RETURNS HTML VIEWS
public class AuthController {

    private final UserService userService;


    public AuthController(UserService userService) {

        this.userService = userService;
    }


    @GetMapping("/signup")
    public String ShowSignupPage(Principal principal, Model model) {

        if(principal != null){
            return "redirect:/dashboard";
        }

        model.addAttribute("user", new User());
        return "signup"; // MATCHES signup.html IN /TEMPLATES
    }

    @GetMapping("/login")
    public String showLoginPage(Principal principal, Model model) {

        if(principal != null){
            return "redirect:/dashboard";
        }
        return "login"; // MATCHES login.html IN /TEMPLATES
    }




    @PostMapping("/register")
    public String registerUser(@ModelAttribute ("user") @Valid User user,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes
                               ) {

        System.out.println(">>> Received user from form: " + user);

        if(bindingResult.hasErrors()) {
            return "signup";
        }

        userService.registerUser(user);
        redirectAttributes.addFlashAttribute("message" , "Registration successful");
        return "redirect:/login";
    }


}
