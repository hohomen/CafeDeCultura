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
        
    @Transactional(isolation=Isolation.READ_COMMITTED)
    @Override
    public List<ReplyVO> getList(Integer boardId) throws Exception {
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
    
    @Transactional
    @Override
    public void registerReply(ReplyVO vo) throws Exception {
        if (vo.getReplyId() == null || "".equals(vo.getReplyId())) {
            if (vo.getReParent() != null) {
                ReplyVO replyInfo = replyDAO.getReplyParent(vo.getReParent());
                vo.setReDepth(replyInfo.getReDepth());
                vo.setReOrder(replyInfo.getReOrder() + 1);
                replyDAO.setReplyOrder(replyInfo);
            } else {
                Integer reOrder = replyDAO.getBoardReplyMaxOrder(vo.getBoardId());                
                vo.setReOrder(reOrder);
            }
            replyDAO.createReply(vo);
            boardDAO.updateReplyCnt(vo.getBoardId(), 1);
        }        
    }    
}
