
package com.cultura.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cultura.aop.LogAdvice;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);
    
    @RequestMapping(value = "/login/loginForm", method = RequestMethod.GET)
    public void login(String error, String logout, Model model)throws Exception {        
        logger.info("error:"+ error);
        logger.info("error:"+ logout);
        
        if(error != null) {
            model.addAttribute("error", "Check Your Account");
            model.addAttribute("logout", "Logout !!");
        }
    }
}
