package com.cultura.controller;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cultura.model.BoardVO;
import com.cultura.model.Criteria;
import com.cultura.model.LikeVO;
import com.cultura.service.BoardService;
import com.cultura.service.ReplyService;
import com.cultura.util.BoardImageUpload;

@Controller
public class BoardController {   
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    
    @Inject
    private BoardService boardService;
    @Inject
    private ReplyService replyService;    
    
    @RequestMapping(value = "/board/read", method = RequestMethod.GET)
    public void read(@RequestParam("boardId") int boardId, 
                     @ModelAttribute("cri") Criteria cri, Model model) throws Exception {        
        model.addAttribute(boardService.read(boardId));
        model.addAttribute("replies",replyService.getList(boardId));
    }

    @RequestMapping(value = "/board/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("boardId") int boardId, 
                         Criteria cri, RedirectAttributes rttr) throws Exception {        
      boardService.remove(boardId);
      
      rttr.addAttribute("page", cri.getPage());
      rttr.addAttribute("perPageNum", cri.getPerPageNum());
      rttr.addAttribute("searchType", cri.getSearchType());
      rttr.addAttribute("keyword", cri.getKeyword());
      rttr.addFlashAttribute("msg", "SUCCESS");
      return "redirect:/home";
    }

    @RequestMapping(value = "/board/modify", method = RequestMethod.GET)
    public void modifyPagingGET(int boardId, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
      model.addAttribute(boardService.read(boardId));
    }

    @RequestMapping(value = "/board/modify", method = RequestMethod.POST)
    public String modifyPagingPOST(BoardVO board, Criteria cri,
                                   RedirectAttributes rttr) throws Exception {      
      boardService.update(board);
      
      rttr.addAttribute("page", cri.getPage());
      rttr.addAttribute("perPageNum", cri.getPerPageNum());
      rttr.addAttribute("searchType", cri.getSearchType());
      rttr.addAttribute("keyword", cri.getKeyword());
      rttr.addFlashAttribute("msg", "SUCCESS");
      return "redirect:/home";
    }

    @RequestMapping(value = "/board/write", method = RequestMethod.GET)
    public void registGET() throws Exception {
    }

    @RequestMapping(value = "/board/write", method = RequestMethod.POST)
    public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception { 
        boardService.regist(board);
        rttr.addFlashAttribute("msg", "SUCCESS");
        return "redirect:/home";
    }
    
    @RequestMapping(value = "/like/board/{boardId}/userId/{userId}", method = RequestMethod.POST)
    public ResponseEntity<String> registerLikePOST(@PathVariable("boardId") Integer boardId,
            @PathVariable("userId") String userId) throws Exception{ 
        
        LikeVO likeVO = new LikeVO(boardId, userId);
        boardService.registerLike(likeVO);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/like/board/{boardId}/userId/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeLikeDELETE(@PathVariable("boardId") Integer boardId,
            @PathVariable("userId") String userId) throws Exception{
        
        LikeVO likeVO = new LikeVO(boardId, userId);
        boardService.removeLike(likeVO);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/like/board/{boardId}/userId/{userId}", method = RequestMethod.GET)
    public ResponseEntity<String> isLikedBoardGET(@PathVariable("boardId") Integer boardId,
            @PathVariable("userId") String userId) throws Exception{
        
        LikeVO likeVO = new LikeVO(boardId, userId);
        if(boardService.checkLikedBoard(likeVO) == 1){
            return new ResponseEntity<>("Liked_Board", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }
        
    
    @Resource(name = "boardImagePath")
    private String boardImagePath;    
    @ResponseBody
    @RequestMapping(value ="/board/boardImage", method=RequestMethod.POST,
                    produces = "text/plain;charset=UTF-8")  
    public ResponseEntity<String> uploadBoardImage(MultipartFile file)throws Exception{
      
      logger.info("originalName: " + file.getOriginalFilename());
     
      return new ResponseEntity<>(
                     BoardImageUpload.uploadFile(boardImagePath,
                     file.getOriginalFilename(),
                     file.getBytes()),
                     HttpStatus.CREATED);
    }    
}
