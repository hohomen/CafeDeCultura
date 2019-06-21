package com.cultura.service;

import java.util.List;

import com.cultura.model.ReplyVO;

public interface ReplyService {
    
    public List<ReplyVO> getList(Integer board_id) throws Exception;    
    public void updateReply(ReplyVO vo) throws Exception;        
    public void deleteReply(ReplyVO vo) throws Exception;
    public void registerReply(ReplyVO vo) throws Exception;

}
