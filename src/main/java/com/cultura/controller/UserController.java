package com.cultura.controller;

import javax.annotation.Resource;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cultura.model.AuthVO;
import com.cultura.model.UserVO;
import com.cultura.persistence.UserDAO;
import com.cultura.service.UserService;
import com.cultura.util.ProfileImageUpload;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/user/loginForm", method = RequestMethod.GET)
    public void loginGET(String error, String logout, Model model) throws Exception {
        logger.info("error:" + error);
        logger.info("error:" + logout);

        if (error != null) {
            model.addAttribute("error", "Check Your Account");
            model.addAttribute("logout", "Logout !!");
        }
    }

    @RequestMapping(value = "/user/joinForm", method = RequestMethod.GET)
    public void joinFormGET(Model model) throws Exception {
    }

    @Inject
    private UserDAO dao;
    @ResponseBody
    @RequestMapping(value = "/checkId/{userId}", method = RequestMethod.GET)
    public ResponseEntity<String> checkId(@PathVariable("userId") String userId) throws Exception {        
        if(dao.readId(userId) != null)
            return new ResponseEntity<>("Used_ID", HttpStatus.OK);
        else
            return new ResponseEntity<>("Usable_Id", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/checkNickname/{nickname}", method = RequestMethod.GET)
    public ResponseEntity<String> checkNickname(@PathVariable("nickname") String nickname) throws Exception {            
        if(dao.readNickname(nickname) != null)
            return new ResponseEntity<>("Used_Nick", HttpStatus.OK);
        else
            return new ResponseEntity<>("Usable_Nick", HttpStatus.OK);
    }
        
    @Inject    
    private UserService service;
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String registPOST(UserVO user, AuthVO auth, RedirectAttributes rttr) throws Exception {             
        service.createID(user, auth);
        return "redirect:/home";
    }
    
    @RequestMapping(value = "/user/userInfo", method = RequestMethod.POST)
    public void readUser(@RequestParam("userId") String userId) throws Exception{
        
    }
    
    @RequestMapping(value = "/user/{userId}", method = {RequestMethod.PUT, RequestMethod.PATCH })
    public ResponseEntity<String> modifyUserInfo(@PathVariable("userId") String userId, @RequestBody UserVO user) throws Exception {
        ResponseEntity<String> entity = null;
        service.modifyUserInfo(user);
        entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        return entity;
    }
    
    @Resource(name = "profileImagePath") private String profileImagePath;    
    @ResponseBody
    @RequestMapping(value ="/user/uploadProfile", method=RequestMethod.POST, 
                    produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> uploadAjax(MultipartFile file)throws Exception{
      
      logger.info("originalName: " + file.getOriginalFilename());    
     
      return new ResponseEntity<>(
                     ProfileImageUpload.uploadFile(profileImagePath,
                     file.getOriginalFilename(), 
                     file.getBytes()), 
                     HttpStatus.CREATED);
    }
    
}