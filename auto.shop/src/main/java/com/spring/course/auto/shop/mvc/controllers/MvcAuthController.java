package com.spring.course.auto.shop.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600, exposedHeaders = {"Authorization"})
@Controller
public class MvcAuthController {

    @GetMapping("login")
    public String getLoginPage(Model model, ServletResponse response) {
        ((HttpServletResponse) response).setHeader("Authorization", "hueta");
        model.addAttribute("message", "Hello");
        return "login";
    }

    @PostMapping("login")
    public String postLogin(Map<String, Object> model) {
        return "registration";
    }

    @GetMapping("registration")
    public String getRegistrationPage(Model model, ServletResponse response) {
        return "registration";
    }
}
