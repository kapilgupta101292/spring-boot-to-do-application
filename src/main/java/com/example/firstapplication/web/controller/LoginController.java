package com.example.firstapplication.web.controller;

import com.example.firstapplication.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        model.put("name", "in28Minutes");

        return "welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showWelcomeMessage(ModelMap model, @RequestParam String name, @RequestParam String password){
//        System.out.println("Name is " + name );
        boolean isValid = loginService.validateUser(name, password);
        if(isValid) {
            model.put("name", name);
            model.put("password", password);

            return "welcome";
        }else{
            model.put("errorMessage","Invalid Credentials");
            return "login";
        }
    }
}
