package com.cultura.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cultura.domain.BoardVO;
import com.cultura.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public void RegisterGET(BoardVO board, Model model) throws Exception {
		logger.info("register get ...........");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception{
		logger.info("create post .........");
		logger.info(board.toString());
		
		service.create(board);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/home";
	}
	
	@RequestMapping(value ="/read", method = RequestMethod.GET)
	public void read(@RequestParam("board_id") int board_id, Model model)throws Exception{
		logger.info("show read ...........");
		model.addAttribute(service.read(board_id));
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	  public String remove(@RequestParam("board_id") int board_id, RedirectAttributes rttr) throws Exception {

	    service.delete(board_id);

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    return "redirect:/home";
	  }
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	  public void modifyGET(int board_id, Model model) throws Exception {

	    model.addAttribute(service.read(board_id));
	  }
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	  public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception {

	    logger.info("mod post............");

	    service.update(board);
	    rttr.addFlashAttribute("msg", "SUCCESS");

	    return "redirect:/home";
	  }	
}

