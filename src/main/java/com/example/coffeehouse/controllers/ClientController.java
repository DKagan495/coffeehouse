package com.example.coffeehouse.controllers;

import com.example.coffeehouse.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ClientController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    ClientService clientService;

    @GetMapping("/clients/{id}")
    public String toClientPage(@PathVariable int id, Model model){
        model.addAttribute("client", clientService.toClientPage(id));
        return "client";
    }
    @GetMapping("/me")
    public String toMyPage(){
        System.out.println(httpSession.getAttribute("USER_ID"));
        return "redirect:/clients/" + httpSession.getAttribute("USER_ID");
    }
    @GetMapping("/clients")
    public String getClientList(Model model){
        System.out.println(clientService.toClientList().get(1).getName());
        model.addAttribute("clients", clientService.toClientList());
        return "clientlist";
    }
}
