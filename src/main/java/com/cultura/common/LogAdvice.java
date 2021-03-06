package com.cultura.common;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LogAdvice {    
    private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);
        
    @After("execution(* com.cultura.controller.*.*(..))")
    public void logParameter(JoinPoint jp) {        
        logger.info("--------A call occurred--------");
        logger.info(jp.getTarget() + jp.getSignature().getName());
        logger.info(Arrays.toString(jp.getArgs()));        
    }
}
