package com.cultura.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cultura.domain.PageMaker;
import com.cultura.domain.SearchCriteria;
import com.cultura.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private BoardService service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);

        model.addAttribute("list", service.listSearchCriteria(cri));
        
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        
        pageMaker.setTotalCount(service.listSearchCount(cri));
        
        model.addAttribute("pageMaker", pageMaker);
		
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		
		logger.info("show all list ...........");
		model.addAttribute("list", service.listAll());	
		
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
    public void ajaxTest() throws Exception{
        
	    
    }
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public void ajaxTest2() throws Exception{
        
        
    }
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public void ajaxTest3() throws Exception{
        
        
    }

}
