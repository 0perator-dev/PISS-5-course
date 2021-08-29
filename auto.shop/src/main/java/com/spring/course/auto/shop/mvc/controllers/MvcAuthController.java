package com.spring.course.auto.shop.mvc.controllers;

import com.spring.course.auto.shop.models.dtos.requests.UserToRegister;
import com.spring.course.auto.shop.services.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600, exposedHeaders = {"Authorization"})
@Controller
public class MvcAuthController {

    @Autowired
    IAuthService authService;


    @GetMapping("login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping("/login-success")
    public String loginSuccess(Model model) {
        model.addAttribute("loginSuccess", true);
        return "login";
    }

    @GetMapping("registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserToRegister());

        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@ModelAttribute("user") UserToRegister user, Model model) {
        try {
            if (!user.isValid()) {
                throw new IllegalArgumentException();
            }

            authService.register(user);
            model.addAttribute("registrationSuccess", true);
        } catch (Exception exception) {
            model.addAttribute("registrationError", true);
        }

        return "registration";
    }

}
