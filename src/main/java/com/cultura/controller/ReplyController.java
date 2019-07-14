package com.cultura.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cultura.model.ReplyVO;
import com.cultura.service.ReplyService;

@RestController
public class ReplyController {
    
    @Inject
    private ReplyService replyService; 

    @RequestMapping(value = "/replies", method = RequestMethod.POST)
    public ResponseEntity<String> registerReplyPost(@RequestBody ReplyVO vo) throws Exception {        
        replyService.registerReply(vo);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/replies/all/{boardId}", method = RequestMethod.GET)
    public ResponseEntity<List<ReplyVO>> getReplylist(@PathVariable("boardId") Integer boardId) throws Exception {        
        ResponseEntity<List<ReplyVO>> entity = null;      
        entity = new ResponseEntity<>(replyService.getList(boardId), HttpStatus.OK);
        return entity;
    }
    
    @RequestMapping(value = "/replies/{replyId}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    public ResponseEntity<String> updateReply(@PathVariable("replyId") Integer replyId,
            @RequestBody ReplyVO vo) throws Exception {        
        vo.setReplyId(replyId);
        replyService.updateReply(vo);        
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/replies/{replyId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeReply(@PathVariable("replyId") Integer replyId,
            @RequestBody ReplyVO vo) throws Exception {        
        vo.setReplyId(replyId);
        replyService.deleteReply(vo);         
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
    
    
}
