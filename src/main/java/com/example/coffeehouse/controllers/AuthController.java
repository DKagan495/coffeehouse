package com.example.coffeehouse.controllers;

import com.example.coffeehouse.models.Client;
import com.example.coffeehouse.models.constkits.AuthResult;
import com.example.coffeehouse.services.ClientAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthController {

    private HttpSession httpSession;

    private ClientAuthorizationService clientAuthorizationService;

    public AuthController(HttpSession httpSession, ClientAuthorizationService clientAuthorizationService) {
        this.httpSession = httpSession;
        this.clientAuthorizationService = clientAuthorizationService;
    }

    @GetMapping("/reg")
    public String regFormMapping(Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") == AuthResult.VALID)
            return "redirect:/me";
        model.addAttribute("client", new Client());
        return "regform";
    }
    @PostMapping("/reg")
    public String regMapping(@ModelAttribute @Valid Client client, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "regform";
        clientAuthorizationService.addClientToDataBase(client);
        httpSession.setAttribute("USER_ID", client.getId());
        System.out.println("Session id during registration is " + httpSession.getAttribute("USER_ID"));
        httpSession.setAttribute("USER_ROLE", "client");
        httpSession.setAttribute("AUTHORIZATION_RESULT_CLIENT", AuthResult.VALID);
        return "redirect:/me";
    }
    @GetMapping("/auth")
    public String logInFormMapping(){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") == AuthResult.VALID)
            return "redirect:/me";
        return "loginform";
    }
    @PostMapping("/auth")
    public String logInMapping(@RequestParam String email, @RequestParam String password){
        if(clientAuthorizationService.checkLogInParameters(email, password) != null){
            httpSession.setAttribute("USER_ID", clientAuthorizationService.checkLogInParameters(email, password).getId());
            httpSession.setAttribute("USER_ROLE", "client");
            httpSession.setAttribute("AUTHORIZATION_RESULT_CLIENT", AuthResult.VALID);
            return "redirect:/clients/" + clientAuthorizationService.checkLogInParameters(email, password).getId();
        }
        httpSession.setAttribute("AUTHORIZATION_RESULT_CLIENT", AuthResult.INVALID);
        return "redirect:/auth";
    }
}
