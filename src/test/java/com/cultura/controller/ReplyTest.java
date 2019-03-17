package com.cultura.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cultura.domain.ReplyVO;
import com.cultura.persistence.ReplyDAO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class ReplyTest {
    
    @Inject
    private ReplyDAO dao;

    private static Logger logger = LoggerFactory.getLogger(ReplyTest.class);

    @Test
    public void testCreate() throws Exception {

      ReplyVO reply = new ReplyVO();
      reply.setBoard_id(4094);
      reply.setReplytext("Hello World");
      reply.setReplyer("hohomen");
      dao.create(reply);
    }

    @Test
    public void testList() throws Exception {

      logger.info(dao.list(3067).toString());
    }

    @Test
    public void testUpdate() throws Exception {

      ReplyVO reply = new ReplyVO();
      reply.setBoard_id(4094);
      reply.setReplytext("Hello World!!");      
      dao.update(reply);
    }

    @Test
    public void testDelete() throws Exception {
      dao.delete(25);
    }

}
