package com.cultura.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cultura.domain.BoardVO;
import com.cultura.domain.Criteria;
import com.cultura.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Inject
	private SqlSession session;	
	private static String namespace = "com.cultura.mapper.BoardMapper";
	
	@Override
	public void create(BoardVO vo) throws Exception {
		session.insert(namespace+".create", vo);		
	}

	@Override
	public BoardVO read(Integer boardId) throws Exception {
		return session.selectOne(namespace+".read", boardId);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		session.update(namespace+".update", vo);		
	}

	@Override
	public void delete(Integer boardId) throws Exception {
		session.delete(namespace+".delete", boardId);		
	}	

    @Override
    public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
        return session.selectList(namespace+ ".listSearch", cri);
    }

    @Override
    public int listSearchCount(SearchCriteria cri) throws Exception {
        return session.selectOne(namespace+ ".listSearchCount", cri);
    }

    @Override
    public void updateReplyCnt(Integer boardId, int amount) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        paramMap.put("boardId", boardId);
        paramMap.put("amount", amount);
        
        session.update(namespace + ".updateReplyCnt", paramMap);        
    }

    @Override
    public void updateViewCnt(Integer boardId) throws Exception {
        session.update(namespace+".updateViewCnt", boardId);        
    }
	
}
