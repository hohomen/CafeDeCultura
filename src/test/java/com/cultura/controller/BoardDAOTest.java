package com.cultura.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cultura.model.BoardVO;
import com.cultura.model.Criteria;
import com.cultura.model.LikeVO;
import com.cultura.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardDAOTest {

  @Inject
  private BoardDAO dao;

  private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

  @Test @Ignore
  public void testCreate() throws Exception {

    BoardVO board = new BoardVO();
    board.setTitle("안녕. ");
    board.setContent("친구들. ");
    board.setUserId("user00");
    dao.create(board);
  }

  @Test @Ignore
  public void testRead() throws Exception {

    logger.info(dao.read(2).toString());
  }

  @Test @Ignore
  public void testUpdate() throws Exception {

    BoardVO board = new BoardVO();
    board.setBoardId(1);
    board.setTitle("헬로");
    board.setContent("월드");
    dao.update(board);
  }

  @Test @Ignore
  public void testDelete() throws Exception {

    dao.delete(1);
  }
  
  @Test @Ignore
  public void testListPage()throws Exception{
      int page =3;
      /*List<BoardVO> list = dao.listPage(page);
      
      for(BoardVO boardVO : list){
          logger.info(boardVO.getBoardId() + ":" + boardVO.getTitle());
      }*/
  }
  
  @Test @Ignore
  public void testListCriteria()throws Exception{
      Criteria cri = new Criteria();
      cri.setPage(2);
      cri.setPerPageNum(20);
      
      /*List<BoardVO> list = dao.listCriteria(cri);
      
      for(BoardVO boardVO : list){
          logger.info(boardVO.getBoardId()+":"+boardVO.getTitle());
      }      */
  }
  
  @Test @Ignore
  public void testDynamic1() throws Exception{
      
      Criteria cri = new Criteria();
      cri.setPage(1);
      cri.setKeyword("글");
      cri.setSearchType("t");
      
      logger.info("===============================");
      
      List<BoardVO> list = dao.listSearch(cri);
      
      for(BoardVO boardVO : list){
          logger.info(boardVO.getBoardId()+": "+ boardVO.getTitle());          
      }
      
      logger.info("===============================");

      logger.info("Count: " + dao.listSearchCount(cri));
  }
  
  @Test
  public void addLikeTest() throws Exception{
      LikeVO vo = new LikeVO();
      vo.setUserId("choi4");
      vo.setBoardId(123);
      dao.createLike(vo);
      dao.updateLikeCnt(vo.getBoardId(), 1);
  }
}
