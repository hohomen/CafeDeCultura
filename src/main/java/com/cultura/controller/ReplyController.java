package com.cultura.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cultura.domain.ReplyVO;
import com.cultura.service.ReplyService;

@Controller
@RequestMapping("/replies")
public class ReplyController {
    
    @Inject
    private ReplyService service;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody ReplyVO vo) {

      ResponseEntity<String> entity = null;
      try {
        service.create(vo);
        entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
      } catch (Exception e) {
        e.printStackTrace();
        entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
      return entity;
    }
    
    @RequestMapping(value = "/all/{boardId}", method = RequestMethod.GET)
    public ResponseEntity<List<ReplyVO>> list(@PathVariable("boardId") Integer boardId) {

      ResponseEntity<List<ReplyVO>> entity = null;
      try {
        entity = new ResponseEntity<>(service.list(boardId), HttpStatus.OK);

      } catch (Exception e) {
        e.printStackTrace();
        entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

      return entity;
    }
    
    @RequestMapping(value = "/{replyId}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    public ResponseEntity<String> update(@PathVariable("replyId") Integer replyId, @RequestBody ReplyVO vo) {

      ResponseEntity<String> entity = null;
      try {
        vo.setReplyId(replyId);
        service.update(vo);

        entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
      } catch (Exception e) {
        e.printStackTrace();
        entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
      return entity;
    }
    
    @RequestMapping(value = "/{replyId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove(@PathVariable("replyId") Integer replyId) {

      ResponseEntity<String> entity = null;
      try {
        service.delete(replyId);
        entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
      } catch (Exception e) {
        e.printStackTrace();
        entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
      return entity;
    }
}
