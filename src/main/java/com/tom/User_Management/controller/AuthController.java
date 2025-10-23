package com.tom.User_Management.controller;

import com.tom.User_Management.dto.UserDTO;
import com.tom.User_Management.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Slf4j
@Controller // TELLS SPRING THIS CLASS HANDLES WEB REQUESTS AND RETURNS HTML VIEWS
public class AuthController {

  private final UserService userService;

  public AuthController(UserService userService) {

    this.userService = userService;
  }

  // READ IN CRUD
  @GetMapping("/signup")
  public String showSignupPage(Principal principal, Model model) {

    if (principal != null) {
      return "redirect:/dashboard";
    }
    model.addAttribute("user", new UserDTO());
    return "signup"; // MATCHES signup.html IN /TEMPLATES
  }


  // READ IN CRUD
  @GetMapping("/login")
  public String showLoginPage(Principal principal, Model model) {

    if (principal != null) {
      return "redirect:/dashboard";
    }
    return "login";
  }

    // READ IN CRUD
    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        else {
            model.addAttribute("username", "Guest");
        }

        return "dashboard";
    }



  // CREATE IN CRUD
  @PostMapping("/register")
  public String registerUser(
      @ModelAttribute("user") @Valid UserDTO userDTO,
      BindingResult bindingResult,
      Model model,
      RedirectAttributes redirectAttributes) {

    log.info(">>> Received user from form: {}", userDTO);

    if (bindingResult.hasErrors()) {
      return "signup";
    }

    userService.registerUser(userDTO);
    redirectAttributes.addFlashAttribute("message", "Registration successful");
    return "redirect:/login";
  }
}
