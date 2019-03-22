package com.cultura.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/sboard/*")
public class SearchBoardController {
    
    private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
    
    @Inject
    private BoardService service;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model)throws Exception{
        logger.info(cri.toString());
        
        model.addAttribute("list", service.listSearchCriteria(cri));
        
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        
        pageMaker.setTotalCount(service.listSearchCount(cri));
        
        model.addAttribute("pageMaker", pageMaker);
        
    }
    
    @RequestMapping(value = "/readPage", method = RequestMethod.GET)
    public void read(@RequestParam("board_id") int board_id, @ModelAttribute("cri") SearchCriteria cri, Model model)
        throws Exception {
        logger.info(cri.toString());
      model.addAttribute(service.read(board_id));
    }

    @RequestMapping(value = "/removePage", method = RequestMethod.POST)
    public String remove(@RequestParam("board_id") int board_id, SearchCriteria cri, RedirectAttributes rttr) throws Exception {

      service.delete(board_id);

      rttr.addAttribute("page", cri.getPage());
      rttr.addAttribute("perPageNum", cri.getPerPageNum());
      rttr.addAttribute("searchType", cri.getSearchType());
      rttr.addAttribute("keyword", cri.getKeyword());

      rttr.addFlashAttribute("msg", "SUCCESS");

      return "redirect:/sboard/list";
    }

    @RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
    public void modifyPagingGET(int board_id, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

      model.addAttribute(service.read(board_id));
    }

    @RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
    public String modifyPagingPOST(BoardVO board, SearchCriteria cri, RedirectAttributes rttr) throws Exception {

      logger.info(cri.toString());
      service.update(board);

      rttr.addAttribute("page", cri.getPage());
      rttr.addAttribute("perPageNum", cri.getPerPageNum());
      rttr.addAttribute("searchType", cri.getSearchType());
      rttr.addAttribute("keyword", cri.getKeyword());

      rttr.addFlashAttribute("msg", "SUCCESS");

      logger.info(rttr.toString());

      return "redirect:/sboard/list";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registGET() throws Exception {

      logger.info("regist get ...........");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception {

      logger.info("regist post ...........");
      logger.info(board.toString());

      service.create(board);

      rttr.addFlashAttribute("msg", "SUCCESS");

      return "redirect:/sboard/list";
    }

}
