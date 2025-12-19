package com.tom.User_Management.controller;

import com.tom.User_Management.dto.UpdateUserDTO;
import com.tom.User_Management.dto.UserDTO;
import com.tom.User_Management.model.User;
import com.tom.User_Management.service.AdminService;
import com.tom.User_Management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

// HANDLES ADMIN LOGIN AND DASHBOARD PAGE ROUTING.
@Controller("/admin")
public class AdminController {

  @Autowired private AdminService adminService;
  @Autowired private UserService userService;

  // READ IN CRUD
  @GetMapping("/admin-dashboard")
  public String showAdminDashboard(
      Model model, @RequestParam(defaultValue = "0") int page, Principal principal) {

    Page<User> userPage = adminService.getUsersPaginated(page, 5);

    model.addAttribute("users", userPage.getContent());
    model.addAttribute("totalPages", userPage.getTotalPages());
    model.addAttribute("currentPage", page);

    model.addAttribute("username", principal != null ? principal.getName() : "Guest");

    return "admin-dashboard";
  }

  @GetMapping("/search")
  public String serachUsers(
      @RequestParam("keyword") String keyword,
      @RequestParam(defaultValue = "0") int page,
      Model model,
      Principal principal) {

    Page<User> userPage = adminService.searchUsersPaginated(keyword, page, 5);

    model.addAttribute("users", userPage.getContent());
    model.addAttribute("totalPages", userPage.getTotalPages());
    model.addAttribute("currentPage", page);
    model.addAttribute("keyword", keyword);
    model.addAttribute("username", principal != null ? principal.getName() : "Guest");

    return "admin-dashboard";
  }



  // DELETE IN CRUD
  @GetMapping("/delete/{id}")
  public String deleteUser(
      @PathVariable("id") Long id,
      Principal principal,
      RedirectAttributes redirectAttributes) {

    User currentUser = adminService.getUserByName(principal.getName());

    if (currentUser != null && currentUser.getUserId().equals(id)) {
      redirectAttributes.addFlashAttribute(
          "errorMessage", "You cannot delete your own admin account.");
      return "redirect:/admin-dashboard";
    }

    try {
      adminService.deleteUser(id);
      redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }
    return "redirect:/admin-dashboard";
  }

  // SHOWING THE ADD USER PAGE FOR ADMIN
  @GetMapping("/adduser")
  public String showAddUser(Model model){
      model.addAttribute("user", new UserDTO());
      return "add-user";
  }

  //TO REGISTER NEW USER BY ADMIN
  @PostMapping("/adduser")
    public String addUser(
          @ModelAttribute("user") @Valid
          UserDTO userDTO,
          BindingResult bindingResult,
          Model model,
          RedirectAttributes redirectAttributes
  ){
      if (bindingResult.hasErrors()){
          redirectAttributes.addFlashAttribute("errorMessage", "Failed to add a new user");
          return "admin-dashboard";
      }

      userService.registerUser(userDTO);
      redirectAttributes.addFlashAttribute("successMessage", "New user added");
      return "redirect:/admin-dashboard";
  }

  // TO GET EDIT DETAILS PAGE OF REGISTERED USERS
  @GetMapping("/edit/{id}")
  public String showEditUser(
          @PathVariable Long id,
          Model model){

      UserDTO userDTO =  adminService.getUserById(id);
      model.addAttribute("user", userDTO);
      return "edit-user";
  }

  // TO SAVE USER UPDATED DETAILS IN DB
  @PostMapping("/updateUser/{id}")
  public String updateUser(
          @PathVariable Long id, // Extracts the {id} from the URL
          @ModelAttribute("user") @Valid UpdateUserDTO updateUserDTO, // Spring takes all form fields from the view and converts them into a UserDTO object
          RedirectAttributes redirectAttributes
  ){
      adminService.updateUser(id, updateUserDTO);
      redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
      return "redirect:/admin-dashboard";
  }
}
