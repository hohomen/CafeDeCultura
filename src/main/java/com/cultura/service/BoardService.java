package com.cultura.service;

import java.util.List;

import com.cultura.domain.BoardVO;
import com.cultura.domain.Criteria;
import com.cultura.domain.SearchCriteria;

public interface BoardService {
	
	public void create(BoardVO board)throws Exception;
	public BoardVO read(Integer board_id)throws Exception;
	public void update(BoardVO board)throws Exception;
	public void delete(Integer board_id)throws Exception;
	public List<BoardVO> listAll() throws Exception;	
	public List<BoardVO> listCriteria(Criteria cri)throws Exception;
	public int listCountCriteria(Criteria cri)throws Exception;
	public List<BoardVO> listSearchCriteria(SearchCriteria cri)throws Exception;
    public int listSearchCount(SearchCriteria cri)throws Exception;
}
