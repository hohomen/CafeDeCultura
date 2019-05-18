package com.cultura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cultura.domain.BoardVO;
import com.cultura.domain.Criteria;
import com.cultura.domain.SearchCriteria;
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
	public BoardVO read(Integer boardId) throws Exception {
		return dao.read(boardId);
	}

	@Override
	public void update(BoardVO board) throws Exception {
		dao.update(board);		
	}

	@Override
	public void delete(Integer boardId) throws Exception {
		dao.delete(boardId);		
	}

    @Override
    public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
        return dao.listSearch(cri);
    }

    @Override
    public int listSearchCount(SearchCriteria cri) throws Exception {
        return dao.listSearchCount(cri);
    }  

	
}
