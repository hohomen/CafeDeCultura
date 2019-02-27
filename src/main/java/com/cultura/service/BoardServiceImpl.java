package com.cultura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cultura.domain.BoardVO;
import com.cultura.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;
	
	@Override
	public void create(BoardVO board) throws Exception {
		dao.create(board);		
	}

	@Override
	public BoardVO read(Integer board_id) throws Exception {
		return dao.read(board_id);
	}

	@Override
	public void update(BoardVO board) throws Exception {
		dao.update(board);		
	}

	@Override
	public void delete(Integer board_id) throws Exception {
		dao.delete(board_id);		
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	
}
