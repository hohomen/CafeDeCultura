package com.cultura.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cultura.model.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
    
    @Inject
    private SqlSession session;
    
    private static String namespace = "com.cultura.mapper.ReplyMapper";
    
    @Override
    public List<ReplyVO> list(Integer boardId) throws Exception {
        return session.selectList(namespace + ".list", boardId);
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
    public void delete(ReplyVO vo) throws Exception {
    session.update(namespace + ".delete", vo);        
    }

    @Override
    public int getBoardId(Integer replyId) throws Exception {
        return session.selectOne(namespace + ".getBoardId", replyId);
    }

    @Override
    public ReplyVO getReplyParent(String reParent) throws Exception {
        return session.selectOne(namespace + ".selectReplyParent", reParent);
    }

    @Override
    public Integer getBoardReplyMaxOrder(Integer boardId) throws Exception {
        return session.selectOne(namespace + ".selectBoardReplyMaxOrder", boardId);
    }

    @Override
    public void setReplyOrder(ReplyVO replyVO) throws Exception {
        session.update(namespace + ".updateReplyOrder", replyVO);        
    }

    @Override
    public void createReply(ReplyVO vo) throws Exception {
        session.insert(namespace + ".insertReply", vo);        
    }
    
    
    
    
    
}
