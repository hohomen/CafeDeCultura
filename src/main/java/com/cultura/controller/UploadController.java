package com.cultura.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cultura.service.UserService;
import com.cultura.util.BoardImageUpload;
import com.cultura.util.MediaUtils;
import com.cultura.util.ProfileImageUpload;

@Controller
public class UploadController {

  private static final Logger logger = LoggerFactory.getLogger(UploadController.class);  
  @Resource(name = "profileImagePath")
  private String profileImagePath;
  @Resource(name = "boardImagePath")
  private String boardImagePath;
  
  @ResponseBody
  @RequestMapping(value ="/file/boardImage", method=RequestMethod.POST,
                  produces = "text/plain;charset=UTF-8")  
  public ResponseEntity<String> uploadBoardImage(MultipartFile file)throws Exception{
    
    logger.info("originalName: " + file.getOriginalFilename());    
   
    return 
      new ResponseEntity<>(
              BoardImageUpload.uploadFile(boardImagePath,
                file.getOriginalFilename(),
                file.getBytes()),
          HttpStatus.CREATED);
  }
  
  @RequestMapping(value = "/file/uploadAjax", method = RequestMethod.GET)
  public void uploadAjax() {
  }
  
  @ResponseBody
  @RequestMapping(value ="/file/uploadAjax", method=RequestMethod.POST, 
                  produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> uploadAjax(MultipartFile file)throws Exception{
    
    logger.info("originalName: " + file.getOriginalFilename());    
   
    return 
      new ResponseEntity<>(
          ProfileImageUpload.uploadFile(profileImagePath,
                file.getOriginalFilename(), 
                file.getBytes()), 
          HttpStatus.CREATED);
  }
  
  @ResponseBody
  @RequestMapping("/file/displayFile")
  public ResponseEntity<byte[]>  displayFile(String fileName, String pathType)throws Exception{    
    String imagePath; 
    InputStream in = null; 
    ResponseEntity<byte[]> entity = null;
    
    logger.info("FILE NAME: " + fileName);
    
    try{
      if(pathType.equals("userProfile"))
          imagePath = profileImagePath;
      else
          imagePath = boardImagePath;
      String formatName = fileName.substring(fileName.lastIndexOf(".")+1);      
      MediaType mType = MediaUtils.getMediaType(formatName);
      HttpHeaders headers = new HttpHeaders();
      in = new FileInputStream(imagePath+fileName);
      headers.setContentType(mType);
      entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
          headers, 
          HttpStatus.CREATED);
    }catch(Exception e){
      e.printStackTrace();
      entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
    }finally{
      in.close();
    }
      return entity;    
  }
  
  @Autowired
  private UserService userService;
  
  @ResponseBody
  @RequestMapping(value="/file/deleteFile", method=RequestMethod.POST)  
  public ResponseEntity<String> deleteFile(String fileName, String pathType, String userId)throws Exception{    
    logger.info("delete file: "+ fileName);
    String imagePath;
    if(pathType.equals("boardImage"))
        imagePath = boardImagePath;
    else{
        imagePath = profileImagePath;
        String front = fileName.substring(0,12);
        String end = fileName.substring(14);
        new File(imagePath + (front+end).replace('/', File.separatorChar)).delete();
        userService.deleteImage(userId);
    }
    
    new File(imagePath + fileName.replace('/', File.separatorChar)).delete();
    return new ResponseEntity<String>("deleted", HttpStatus.OK);
  }   
}
