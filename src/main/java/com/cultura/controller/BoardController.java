package com.cultura.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cultura.model.BoardVO;
import com.cultura.model.SearchCriteria;
import com.cultura.service.BoardService;
import com.cultura.service.ReplyService;
import com.cultura.util.PageMaker;

@Controller
@RequestMapping("/board/*")
public class BoardController {       
    @Inject
    private BoardService boardService;
    @Inject
    private ReplyService replyService;
    
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void read(@RequestParam("boardId") int boardId, 
                     @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {        
        model.addAttribute(boardService.read(boardId));
        model.addAttribute("replies",replyService.getList(boardId));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("boardId") int boardId, 
                         SearchCriteria cri, RedirectAttributes rttr) throws Exception {        
      boardService.delete(boardId);
      
      rttr.addAttribute("page", cri.getPage());
      rttr.addAttribute("perPageNum", cri.getPerPageNum());
      rttr.addAttribute("searchType", cri.getSearchType());
      rttr.addAttribute("keyword", cri.getKeyword());
      rttr.addFlashAttribute("msg", "SUCCESS");
      return "redirect:/home";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public void modifyPagingGET(int boardId, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
      model.addAttribute(boardService.read(boardId));
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPagingPOST(BoardVO board, SearchCriteria cri,
                                   RedirectAttributes rttr) throws Exception {      
      boardService.update(board);
      
      rttr.addAttribute("page", cri.getPage());
      rttr.addAttribute("perPageNum", cri.getPerPageNum());
      rttr.addAttribute("searchType", cri.getSearchType());
      rttr.addAttribute("keyword", cri.getKeyword());
      rttr.addFlashAttribute("msg", "SUCCESS");
      return "redirect:/home";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public void registGET() throws Exception {
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception { 
      boardService.regist(board);
      rttr.addFlashAttribute("msg", "SUCCESS");
      return "redirect:/home";
    }

}
