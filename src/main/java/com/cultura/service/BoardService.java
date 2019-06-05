package com.cultura.service;

import java.util.List;

import com.cultura.model.BoardVO;
import com.cultura.model.SearchCriteria;

public interface BoardService {
	
	public void regist(BoardVO board)throws Exception;
	public BoardVO read(Integer board_id)throws Exception;
	public void update(BoardVO board)throws Exception;
	public void delete(Integer board_id)throws Exception;
	public List<BoardVO> listSearchCriteria(SearchCriteria cri)throws Exception;
    public int listSearchCount(SearchCriteria cri)throws Exception;
}
