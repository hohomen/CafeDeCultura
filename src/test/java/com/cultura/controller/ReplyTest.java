package com.cultura.controller;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cultura.model.ReplyVO;
import com.cultura.persistence.ReplyDAO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class ReplyTest {
    
    @Inject
    private ReplyDAO dao;
    private Logger logger = LoggerFactory.getLogger(getClass().getName());
    //private static Logger logger = LoggerFactory.getLogger(ReplyTest.class);

    @Test @Ignore
    public void testCreate() throws Exception {

      ReplyVO reply = new ReplyVO();
      reply.setBoardId(4094);
      reply.setReplyText("Hello World");
      reply.setReplyer("hohomen");
      dao.create(reply);
    }

    @Test @Ignore
    public void testList() throws Exception {

      logger.info(dao.list(3067).toString());
    }

    @Test @Ignore
    public void testUpdate() throws Exception {

      ReplyVO reply = new ReplyVO();
      reply.setBoardId(4094);
      reply.setReplyText("Hello World!!");      
      dao.update(reply);
    }

    @Test @Ignore
    public void testDelete() throws Exception {
      //dao.delete(25);
    }
    
    @Test
    public void testRegisterReply(){
        ReplyVO vo = new ReplyVO();
        vo.setReParent("42");
        vo.setBoardId(189); 
        vo.setReplyText("Hello World5");
        vo.setReplyer("choi3");        
    }
    
    @Inject
    private SqlSession sqlSession;
    private static String namespace = "com.cultura.mapper.ReplyMapper";
    @Test 
    public void insertBoardReply() {
        ReplyVO param = new ReplyVO();
        param.setReParent("42");
        param.setBoardId(189); 
        param.setReplyText("Hello World4");
        param.setReplyer("choi3");
        if (param.getReplyId() == null || "".equals(param.getReplyId())) {
            if (param.getReParent() != null) {
                ReplyVO replyInfo = sqlSession.selectOne(namespace+".selectBoard6ReplyParent", param.getReParent());
                param.setReDepth(replyInfo.getReDepth());
                param.setReOrder(replyInfo.getReOrder() + 1);
                sqlSession.selectOne(namespace+".updateBoard6ReplyOrder", replyInfo);
            } else {                
                Integer reOrder = sqlSession.selectOne(namespace+".selectBoard6ReplyMaxOrder", param.getBoardId());
                param.setReOrder(reOrder);
            }
            sqlSession.insert(namespace+".insertBoard6Reply", param);
        }
    }
}
