package com.cultura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cultura.model.Criteria;
import com.cultura.model.PageMaker;
import com.cultura.service.BoardService;

@Controller
public class HomeController {		
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
	    model.addAttribute("list", service.listSearchCriteria(cri));
	    
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);        
        pageMaker.setTotalCount(service.listSearchCount(cri));        
        model.addAttribute("pageMaker", pageMaker);
        
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String listAll(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
	    model.addAttribute("list", service.listSearchCriteria(cri));
	    
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);        
        pageMaker.setTotalCount(service.listSearchCount(cri));
        model.addAttribute("pageMaker", pageMaker);
        return "home";
    }
}
