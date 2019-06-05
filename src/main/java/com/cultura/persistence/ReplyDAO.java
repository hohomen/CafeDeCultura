package com.cultura.persistence;

import java.util.List;

import com.cultura.model.ReplyVO;

public interface ReplyDAO {
    public List<ReplyVO> list(Integer board_id) throws Exception;
    public void create(ReplyVO vo) throws Exception;
    public void update(ReplyVO vo) throws Exception;
    public void delete(Integer reply_id) throws Exception;
    public int getBoardId(Integer replyId) throws Exception;    
}
