package com.example.coffeehouse.controllers;

import com.example.coffeehouse.models.constkits.AuthResult;
import com.example.coffeehouse.services.ClientService;
import com.example.coffeehouse.services.MoneyTransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
public class ClientController {

    private final HttpSession httpSession;

    private final ClientService clientService;

    private final MoneyTransferService moneyTransferService;

    public ClientController(HttpSession httpSession, ClientService clientService, MoneyTransferService moneyTransferService) {
        this.httpSession = httpSession;
        this.clientService = clientService;
        this.moneyTransferService = moneyTransferService;
    }


    @GetMapping("/clients/{id}")
    public String toClientPage(@PathVariable int id, Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID && httpSession.getAttribute("AUTHORIZATION_RESULT_EMPLOYEE") != AuthResult.VALID)
            return "redirect:/auth";
        model.addAttribute("client", clientService.toClientPage(id));
        return "client";
    }

    @GetMapping("/me")
    public String toMyPage(){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        System.out.println(httpSession.getAttribute("USER_ID"));
        return "redirect:/clients/" + httpSession.getAttribute("USER_ID");
    }

    @GetMapping("/clients")
    public String getClientList(Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID && httpSession.getAttribute("AUTHORIZATION_RESULT_EMPLOYEE") != AuthResult.VALID)
            return "redirect:/auth";
        model.addAttribute("clients", clientService.toClientList());
        return "clientlist";
    }

    @GetMapping("/getmoney")
    public String getMoneyForm(){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        return "getmoney";
    }

    @PatchMapping("/getmoney")
    public String getMoneyReq(@RequestParam BigDecimal money){
        moneyTransferService.moneyToCurrentClient((long)httpSession.getAttribute("USER_ID"), money);
        return "redirect:/me";
    }

    @GetMapping("/edit")
    public String getUpdateGeneralsForm(Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        model.addAttribute("client", clientService.toClientPage((long)httpSession.getAttribute("USER_ID")));
        return "editpage";
    }

    @PatchMapping("/edit")
    public String saveUpdGeneralsChanges(@RequestParam String name, @RequestParam String surname, @RequestParam int age, @RequestParam String sex){
        clientService.updateClientGeneralInfo((long)httpSession.getAttribute("USER_ID"), name, surname, age, sex);
        return "redirect:/me";
    }

    @GetMapping("/edit/log")
    public String getUpdateLogInParamsForm(Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        model.addAttribute("client", clientService.toClientPage((long)httpSession.getAttribute("USER_ID")));
        return "editlogform";
    }

    @PatchMapping("/edit/log")
    public String saveUpdLogInChanges(@RequestParam String email, @RequestParam String password){
        clientService.updateClientLogInInfo((long)httpSession.getAttribute("USER_ID"), email, password);
        return "redirect:/me";
    }
    @DeleteMapping("/delete")
    public String deleteUser(){
        clientService.deleteCurrentUserAccount((long)httpSession.getAttribute("USER_ID"));
        httpSession.invalidate();
        return "redirect:/auth";
    }
}
