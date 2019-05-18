package com.cultura.persistence;

import java.util.List;

import com.cultura.domain.BoardVO;
import com.cultura.domain.Criteria;
import com.cultura.domain.SearchCriteria;

public interface BoardDAO {
	public void create(BoardVO vo)throws Exception;
	public BoardVO read(Integer boardId)throws Exception;
	public void update(BoardVO vo)throws Exception;
	public void delete(Integer boardId)throws Exception;	
	public List<BoardVO> listSearch(SearchCriteria cri)throws Exception;
	public int listSearchCount(SearchCriteria cri)throws Exception;
	public void updateReplyCnt(Integer boardId, int amount)throws Exception;
	public void updateViewCnt(Integer boardId)throws Exception;
}
