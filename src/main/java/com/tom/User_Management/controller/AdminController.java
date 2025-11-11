package com.tom.User_Management.controller;

import com.tom.User_Management.model.User;
import com.tom.User_Management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

// HANDLES ADMIN LOGIN AND DASHBOARD PAGE ROUTING.

@Controller
public class AdminController {

  @Autowired private AdminService adminService;

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

  // UPDATE IN CRUD

  // DELETE IN CRUD
  @GetMapping("/admin/delete/{id}")
  public String deleteUser(
      @PathVariable("id") Long id, Principal principal, RedirectAttributes redirectAttributes) {

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
}
