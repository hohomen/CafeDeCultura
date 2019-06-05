package com.cultura.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cultura.model.ReplyVO;
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
        boardDAO.updateReplyCnt(vo.getBoardId(), 1);
    }
    
    @Transactional(isolation=Isolation.READ_COMMITTED)
    @Override
    public List<ReplyVO> list(Integer boardId) throws Exception {
        boardDAO.updateViewCnt(boardId);
        return replyDAO.list(boardId);
    }

    @Override
    public void update(ReplyVO vo) throws Exception {
        replyDAO.update(vo);
    }
    
    @Transactional
    @Override
    public void delete(Integer replyId) throws Exception {
        int boardId = replyDAO.getBoardId(replyId);
        replyDAO.delete(replyId);
        boardDAO.updateReplyCnt(boardId, -1);
    }    

}
