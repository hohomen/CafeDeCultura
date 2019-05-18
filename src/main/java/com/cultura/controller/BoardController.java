package com.cultura.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cultura.domain.BoardVO;
import com.cultura.domain.PageMaker;
import com.cultura.domain.SearchCriteria;
import com.cultura.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {       
    @Inject
    private BoardService service;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model)throws Exception{          
        model.addAttribute("list", service.listSearchCriteria(cri));        
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);        
        pageMaker.setTotalCount(service.listSearchCount(cri));        
        model.addAttribute("pageMaker", pageMaker);        
    }
    
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void read(@RequestParam("boardId") int boardId, 
                     @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {        
        model.addAttribute(service.read(boardId));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("boardId") int boardId, 
                         SearchCriteria cri, RedirectAttributes rttr) throws Exception {        
      service.delete(boardId);
      rttr.addAttribute("page", cri.getPage());
      rttr.addAttribute("perPageNum", cri.getPerPageNum());
      rttr.addAttribute("searchType", cri.getSearchType());
      rttr.addAttribute("keyword", cri.getKeyword());
      rttr.addFlashAttribute("msg", "SUCCESS");
      return "redirect:/home";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public void modifyPagingGET(int boardId, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
      model.addAttribute(service.read(boardId));
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPagingPOST(BoardVO board, SearchCriteria cri, RedirectAttributes rttr) throws Exception {      
      service.update(board);
      rttr.addAttribute("page", cri.getPage());
      rttr.addAttribute("perPageNum", cri.getPerPageNum());
      rttr.addAttribute("searchType", cri.getSearchType());
      rttr.addAttribute("keyword", cri.getKeyword());
      rttr.addFlashAttribute("msg", "SUCCESS");      
      return "redirect:/home";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registGET() throws Exception {
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception { 
      service.create(board);
      rttr.addFlashAttribute("msg", "SUCCESS");
      return "redirect:/home";
    }

}
