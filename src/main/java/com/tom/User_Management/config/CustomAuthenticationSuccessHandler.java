package com.tom.User_Management.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

// CLASS USE - HANDLES POST-LOGIN REDIRECTION BASED ON USER ROLE (ADMIN OR NORMAL USER)
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


  // THIS METHOD IS AUTOMATICALLY CALLED WHEN LOGIN IS SUCCESSFUL,
  // IT GIVES ACCESS TO THE AUTHENTICATION OBJECT — WHICH HOLDS THE LOGGED-IN USER DETAILS AND ROLES
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {

      // EXTRACT USER ROLES (AUTHORITIES) FROM AUTHENTICATION OBJECT
      List<String> roles = authentication.getAuthorities()
              .stream()
              .map(GrantedAuthority::getAuthority)// CONVERT EACH ROLE TO STRING
              .toList();

      // REDIRECT BASED ON ROLE
      if(roles.contains("ROLE_ADMIN")){
          response.sendRedirect("/admin-dashboard");
      }
      else{
          response.sendRedirect("/dashboard");
      }
  }
}
