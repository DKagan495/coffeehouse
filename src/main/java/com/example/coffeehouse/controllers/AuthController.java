package com.example.coffeehouse.controllers;

import com.example.coffeehouse.models.Client;
import com.example.coffeehouse.services.ClientAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private ClientAuthorizationService clientAuthorizationService;
   /* public AuthController(ClientAuthorizationService clientAuthorizationService){
        this.clientAuthorizationService = clientAuthorizationService;
    }*/
    @GetMapping("/reg")
    public String regFormMapping(Model model){
        model.addAttribute("client", new Client());
        return "regform";
    }
    @PostMapping("/reg")
    public String regMapping(@ModelAttribute Client client){
        clientAuthorizationService.addClientToDataBase(client);
        return "redirect:/";
    }
    @GetMapping("/auth")
    public String logInFormMapping(){
        return "loginform";
    }
    @PostMapping("/auth")
    public String logInMapping(@RequestParam String email, @RequestParam String password){
        if(clientAuthorizationService.checkLogInParameters(email, password) != null){
            httpSession.setAttribute("USER_ID", clientAuthorizationService.checkLogInParameters(email, password).getId());
            return "redirect:/clients/" + clientAuthorizationService.checkLogInParameters(email, password).getId();
        }
        return "redirect:/auth";
    }
}
