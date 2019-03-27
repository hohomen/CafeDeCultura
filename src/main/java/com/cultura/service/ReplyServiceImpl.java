package com.cultura.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cultura.domain.ReplyVO;
import com.cultura.persistence.BoardDAO;
import com.cultura.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService{
    
    @Inject
    private ReplyDAO replyDAO;
    @Inject
    private BoardDAO boardDAO;
    
    @Transactional
    @Override
    public void create(ReplyVO vo) throws Exception {
        replyDAO.create(vo);
        boardDAO.updateReplyCnt(vo.getBoard_id(), 1);
    }
    
    @Transactional(isolation=Isolation.READ_COMMITTED)
    @Override
    public List<ReplyVO> list(Integer board_id) throws Exception {
        boardDAO.updateViewCnt(board_id);
        return replyDAO.list(board_id);
    }

    @Override
    public void update(ReplyVO vo) throws Exception {
        replyDAO.update(vo);
    }
    
    @Transactional
    @Override
    public void delete(Integer reply_id) throws Exception {
        int board_id = replyDAO.getBoard_id(reply_id);
        replyDAO.delete(reply_id);
        boardDAO.updateReplyCnt(board_id, -1);
    }
    
    

}
