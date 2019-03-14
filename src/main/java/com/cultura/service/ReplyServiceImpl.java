package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.domain.ReplyVO;
import org.zerock.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService{
    
    @Inject
    private ReplyDAO dao;
    
    @Override
    public void create(ReplyVO vo) throws Exception {
        dao.create(vo);        
    }

    @Override
    public List<ReplyVO> list(Integer board_id) throws Exception {
        return dao.list(board_id);
    }

    @Override
    public void update(ReplyVO vo) throws Exception {
        dao.update(vo);
    }

    @Override
    public void delete(Integer reply_id) throws Exception {
        dao.delete(reply_id);
    }
    
    

}
