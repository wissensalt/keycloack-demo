package com.wissensalt.keycloakdemo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

  @GetMapping("/")
  public String index() {

    return "index";
  }

  @GetMapping("/home")
  public String homePage() {

    return "home";
  }

  @GetMapping(path = "/logout")
  public String logout(HttpServletRequest request) throws ServletException {
    request.logout();

    return "/";
  }
}
