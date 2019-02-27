package com.cultura.persistence;

import java.util.List;

import com.cultura.domain.BoardVO;

public interface BoardDAO {
	public void create(BoardVO vo)throws Exception;
	public BoardVO read(Integer board_id)throws Exception;
	public void update(BoardVO vo)throws Exception;
	public void delete(Integer board_id)throws Exception;
	public List<BoardVO> listAll()throws Exception;
}
