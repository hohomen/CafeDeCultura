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
	public BoardVO read(Integer board_id) throws Exception {
		return session.selectOne(namespace+".read", board_id);		
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		session.update(namespace+".update", vo);		
	}

	@Override
	public void delete(Integer board_id) throws Exception {
		session.delete(namespace+".delete", board_id);		
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".listAll");
	}

    @Override
    public List<BoardVO> listPage(int page) throws Exception {
        if (page <= 0){
            page = 1;
        }
        page = (page - 1) * 10;
        return session.selectList(namespace + ".listPage", page);
    }

    @Override
    public List<BoardVO> listCriteria(Criteria cri) throws Exception {
        
        return session.selectList(namespace+".listCriteria", cri);
    }

    @Override
    public int countPaging(Criteria cri) throws Exception {        
        return session.selectOne(namespace+".countPaging", cri);
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
    public void updateReplyCnt(Integer board_id, int amount) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        paramMap.put("board_id", board_id);
        paramMap.put("amount", amount);
        
        session.update(namespace + ".updateReplyCnt", paramMap);        
    }

    @Override
    public void updateViewCnt(Integer board_id) throws Exception {
        session.update(namespace+".updateViewCnt", board_id);        
    }
	
}
