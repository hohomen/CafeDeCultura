package com.cultura.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cultura.domain.AuthVO;
import com.cultura.domain.UserVO;
import com.cultura.persistence.UserDAO;
import com.cultura.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
    public void login(String error, String logout, Model model) throws Exception {
        logger.info("error:" + error);
        logger.info("error:" + logout);

        if (error != null) {
            model.addAttribute("error", "Check Your Account");
            model.addAttribute("logout", "Logout !!");
        }
    }

    @RequestMapping(value = "/joinForm", method = RequestMethod.GET)
    public void join(Model model) throws Exception {

    }

    @Inject
    private UserDAO dao;    
    @RequestMapping(value = "/checkId/{userId}", method = RequestMethod.POST)
    public ResponseEntity<String> checkId(@PathVariable("userId") String userId) throws Exception {
        ResponseEntity<String> entity = null;
        try {
            entity = new ResponseEntity<>(dao.readId(userId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
    
    @RequestMapping(value = "/checkNickname/{nickname}", method = RequestMethod.POST)
    public ResponseEntity<String> checkNickname(@PathVariable("nickname") String nickname) throws Exception {
        ResponseEntity<String> entity = null;
        try {
            entity = new ResponseEntity<>(dao.readNickname(nickname), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
    
    @Inject    
    private UserService service;    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registPOST(UserVO user, AuthVO auth, RedirectAttributes rttr) throws Exception {             
        service.createIdentification(user, auth);        
        return "redirect:/home";
    }
}