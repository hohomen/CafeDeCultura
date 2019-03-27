package com.cultura.persistence;

import java.util.List;

import com.cultura.domain.BoardVO;
import com.cultura.domain.Criteria;
import com.cultura.domain.SearchCriteria;

public interface BoardDAO {
	public void create(BoardVO vo)throws Exception;
	public BoardVO read(Integer board_id)throws Exception;
	public void update(BoardVO vo)throws Exception;
	public void delete(Integer board_id)throws Exception;
	public List<BoardVO> listAll()throws Exception;
	public List<BoardVO> listPage(int page)throws Exception;
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public int countPaging(Criteria cri)throws Exception;
	public List<BoardVO> listSearch(SearchCriteria cri)throws Exception;
	public int listSearchCount(SearchCriteria cri)throws Exception;
	public void updateReplyCnt(Integer board_id, int amount)throws Exception;
	public void updateViewCnt(Integer board_id)throws Exception;
}
