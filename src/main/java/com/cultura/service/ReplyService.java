package org.zerock.service;

import java.util.List;

import org.zerock.domain.ReplyVO;

public interface ReplyService {
    
    public void create(ReplyVO vo) throws Exception;    
    public List<ReplyVO> list(Integer board_id) throws Exception;    
    public void update(ReplyVO vo) throws Exception;        
    public void delete(Integer board_id) throws Exception;

}
