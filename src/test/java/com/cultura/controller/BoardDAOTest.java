package com.cultura.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cultura.domain.BoardVO;
import com.cultura.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardDAOTest {

  @Inject
  private BoardDAO dao;

  private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

  @Test
  public void testCreate() throws Exception {

    BoardVO board = new BoardVO();
    board.setTitle("안녕. ");
    board.setContent("친구들. ");
    board.setMember_id("user00");
    dao.create(board);
  }

  @Test
  public void testRead() throws Exception {

    logger.info(dao.read(2).toString());
  }

  @Test
  public void testUpdate() throws Exception {

    BoardVO board = new BoardVO();
    board.setBoard_id(1);
    board.setTitle("헬로");
    board.setContent("월드");
    dao.update(board);
  }

  @Test
  public void testDelete() throws Exception {

    dao.delete(1);
  }


}
