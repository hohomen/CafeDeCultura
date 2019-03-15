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
    
    @RequestMapping(value = "/all/{board_id}", method = RequestMethod.GET)
    public ResponseEntity<List<ReplyVO>> list(@PathVariable("board_id") Integer board_id) {

      ResponseEntity<List<ReplyVO>> entity = null;
      try {
        entity = new ResponseEntity<>(service.list(board_id), HttpStatus.OK);

      } catch (Exception e) {
        e.printStackTrace();
        entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

      return entity;
    }
    
    @RequestMapping(value = "/{reply_id}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    public ResponseEntity<String> update(@PathVariable("reply_id") Integer reply_id, @RequestBody ReplyVO vo) {

      ResponseEntity<String> entity = null;
      try {
        vo.setReply_id(reply_id);
        service.update(vo);

        entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
      } catch (Exception e) {
        e.printStackTrace();
        entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
      return entity;
    }
    
    @RequestMapping(value = "/{reply_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove(@PathVariable("reply_id") Integer reply_id) {

      ResponseEntity<String> entity = null;
      try {
        service.delete(reply_id);
        entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
      } catch (Exception e) {
        e.printStackTrace();
        entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
      return entity;
    }
}
