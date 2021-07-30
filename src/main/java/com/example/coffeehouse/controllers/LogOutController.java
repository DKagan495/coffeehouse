package com.example.coffeehouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogOutController {

    private final HttpSession httpSession;

    public LogOutController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping("/logout")
    public String logOut(){
        httpSession.invalidate();
        return "redirect:/auth";
    }
}
