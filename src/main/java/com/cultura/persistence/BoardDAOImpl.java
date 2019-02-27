package com.cultura.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cultura.domain.BoardVO;

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
	
	
}
