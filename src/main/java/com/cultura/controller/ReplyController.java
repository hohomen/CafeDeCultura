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
@RequestMapping("/replies")
public class ReplyController {
    
    @Inject
    private ReplyService service;    
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody ReplyVO vo) throws Exception {
        ResponseEntity<String> entity = null;      
        service.registerReply(vo);
        entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);      
        return entity;
    }
    
    @RequestMapping(value = "/all/{boardId}", method = RequestMethod.GET)
    public ResponseEntity<List<ReplyVO>> list(@PathVariable("boardId") Integer boardId) throws Exception {
        ResponseEntity<List<ReplyVO>> entity = null;      
        entity = new ResponseEntity<>(service.getList(boardId), HttpStatus.OK);      
        return entity;
    }
    
    @RequestMapping(value = "/{replyId}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    public ResponseEntity<String> update(@PathVariable("replyId") Integer replyId, @RequestBody ReplyVO vo) throws Exception {
        ResponseEntity<String> entity = null;     
        vo.setReplyId(replyId);
        service.updateReply(vo);
        entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);      
        return entity;
    }
    
    @RequestMapping(value = "/delete/{replyId}", method = RequestMethod.PUT)
    public ResponseEntity<String> remove(@PathVariable("replyId") Integer replyId, @RequestBody ReplyVO vo) throws Exception {
        ResponseEntity<String> entity = null;
        vo.setReplyId(replyId);
        service.deleteReply(vo);
        entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        return entity;
    }
}
