package com.example.coffeehouse.controllers;

import com.example.coffeehouse.models.constkits.AuthResult;
import com.example.coffeehouse.services.EmployeeAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class EmployeeAuthController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    EmployeeAuthorizationService employeeAuthorizationService;

    @GetMapping("/emplauth")
    public String logInFormMapping(){
        return "loginemplform";
    }
    @PostMapping("/emplauth")
    public String logInMapping(@RequestParam String login, @RequestParam String password){
        if(employeeAuthorizationService.checkLogInParameters(login, password) != null){
            httpSession.setAttribute("USER_ID", employeeAuthorizationService.checkLogInParameters(login, password).getId());
            httpSession.setAttribute("USER_NAME", employeeAuthorizationService.checkLogInParameters(login, password).getName());
            httpSession.setAttribute("USER_ROLE", "employee");
            httpSession.setAttribute("AUTHORIZATION_RESULT_EMPLOYEE", AuthResult.VALID);
            return "redirect:/employees/" + employeeAuthorizationService.checkLogInParameters(login, password).getId();
        }
        httpSession.setAttribute("AUTHORIZATION_RESULT_EMPLOYEE", AuthResult.INVALID);
        return "redirect:/emplauth";
    }
}
