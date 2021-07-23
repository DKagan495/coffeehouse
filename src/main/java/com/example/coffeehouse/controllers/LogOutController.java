package com.example.coffeehouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LogOutController {

    @Autowired
    HttpSession httpSession;

    @GetMapping("/logout")
    public String logOut(){
        httpSession.invalidate();
        return "redirect:/auth";
    }
}
