package com.cultura.service;

import java.util.List;

import com.cultura.domain.ReplyVO;

public interface ReplyService {
    
    public void create(ReplyVO vo) throws Exception;    
    public List<ReplyVO> list(Integer board_id) throws Exception;    
    public void update(ReplyVO vo) throws Exception;        
    public void delete(Integer reply_id) throws Exception;

}
