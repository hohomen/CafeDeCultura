package com.cultura.persistence;

import java.util.List;

import com.cultura.domain.BoardVO;
import com.cultura.domain.Criteria;

public interface BoardDAO {
	public void create(BoardVO vo)throws Exception;
	public BoardVO read(Integer board_id)throws Exception;
	public void update(BoardVO vo)throws Exception;
	public void delete(Integer board_id)throws Exception;
	public List<BoardVO> listAll()throws Exception;
	public List<BoardVO> listPage(int page)throws Exception;
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public int countPaging(Criteria cri)throws Exception;
}
