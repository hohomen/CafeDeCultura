package com.cultura.persistence;

import java.util.List;

import com.cultura.model.ReplyVO;

public interface ReplyDAO {
    public List<ReplyVO> list(Integer board_id) throws Exception;
    public void create(ReplyVO vo) throws Exception;
    public void update(ReplyVO vo) throws Exception;
    public void delete(ReplyVO vo) throws Exception;
    public int getBoardId(Integer replyId) throws Exception;
    public ReplyVO getReplyParent(String reParent) throws Exception;
    public Integer getBoardReplyMaxOrder(Integer boardId) throws Exception;
    public void setReplyOrder(ReplyVO replyVO)throws Exception;
    public void createReply(ReplyVO vo) throws Exception;
}
