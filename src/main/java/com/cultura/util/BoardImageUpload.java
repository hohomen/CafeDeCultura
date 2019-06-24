package com.cultura.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class BoardImageUpload {

  private static final Logger logger = 
      LoggerFactory.getLogger(ProfileImageUpload.class);

  public static String uploadFile(String uploadPath, 
                              String originalName, 
                              byte[] fileData)throws Exception{
    
    UUID uid = UUID.randomUUID();    
    String savedName = uid.toString() +"_"+originalName;
    String savedPath = calcPath(uploadPath);    
    File target = new File(uploadPath + savedPath,savedName);
    FileCopyUtils.copy(fileData, target);
    String uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
    return uploadedFileName;
  }

  private static String makeIcon(String uploadPath, String path,
                                    String fileName)throws Exception{    
    String iconName = uploadPath + path + File.separator+ fileName;
    
    return iconName.substring(
        uploadPath.length()).replace(File.separatorChar, '/');
  }
  
  private static String calcPath(String uploadPath) throws IOException{    
    Calendar cal = Calendar.getInstance();    
    String yearPath = File.separator+cal.get(Calendar.YEAR);    
    String monthPath = yearPath + File.separator + 
            new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
    String datePath = monthPath + File.separator +
            new DecimalFormat("00").format(cal.get(Calendar.DATE));
    makeDir(uploadPath, yearPath, monthPath, datePath);
    logger.info(datePath);
    return datePath;
  } 
  
  private static void makeDir(String uploadPath, String... paths) throws IOException{    
    if(new File(paths[paths.length-1]).exists()){
      return;
    }    
    for (String path : paths) {
      File dirPath = new File(uploadPath + path);      
      if(! dirPath.exists() ){
        dirPath.mkdir();
        /*Runtime.getRuntime().exec("chmod 777 " + dirPath);
        dirPath.setExecutable(true, false);
        dirPath.setReadable(true, false);
        dirPath.setWritable(true, false);*/
      } 
    }
  }
  
}