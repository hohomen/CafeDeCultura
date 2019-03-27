package com.cultura.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cultura.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
    
    @Inject
    private SqlSession session;
    
    private static String namespace = "com.cultura.mapper.ReplyMapper";
    
    @Override
    public List<ReplyVO> list(Integer board_id) throws Exception {
        return session.selectList(namespace + ".list", board_id);
    }

    @Override
    public void create(ReplyVO vo) throws Exception {
        session.insert(namespace + ".create", vo);        
    }

    @Override
    public void update(ReplyVO vo) throws Exception {
        session.update(namespace + ".update", vo);        
    }

    @Override
    public void delete(Integer reply_id) throws Exception {
    session.update(namespace + ".delete", reply_id);        
    }

    @Override
    public int getBoard_id(Integer reply_id) throws Exception {
        return session.selectOne(namespace + ".getBoardId", reply_id);
    }
    
    
}
