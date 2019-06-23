package com.cultura.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cultura.model.AuthVO;
import com.cultura.model.UserVO;
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
    public ResponseEntity<Integer> checkId(@PathVariable("userId") String userId) throws Exception {
        ResponseEntity<Integer> entity = null;
        if(dao.readId(userId) != null)
            entity = new ResponseEntity<>(1, HttpStatus.OK);
        else
            entity = new ResponseEntity<>(0, HttpStatus.OK);
        return entity;
    }
    
    @RequestMapping(value = "/checkNickname/{nickname}", method = RequestMethod.POST)
    public ResponseEntity<Integer> checkNickname(@PathVariable("nickname") String nickname) throws Exception {            
        ResponseEntity<Integer> entity = null;        
        if(dao.readNickname(nickname) != null)
            entity = new ResponseEntity<>(1, HttpStatus.OK);
        else
            entity = new ResponseEntity<>(0, HttpStatus.OK);
        return entity;
    }
    
    @Inject    
    private UserService service;
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registPOST(UserVO user, AuthVO auth, RedirectAttributes rttr) throws Exception {             
        service.createID(user, auth);
        return "redirect:/home";
    }
    
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    public void readUser(@RequestParam("userId") String userId) throws Exception{
        
    }
    
    @RequestMapping(value = "/modify/{userId}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    public ResponseEntity<String> modifyUserInfo(@PathVariable("userId") String userId, @RequestBody UserVO user) throws Exception {
        ResponseEntity<String> entity = null;
        service.modifyUserInfo(user);
        entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        return entity;
    }
    
}