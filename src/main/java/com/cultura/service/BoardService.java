package com.cultura.service;

import java.util.List;

import com.cultura.model.BoardVO;
import com.cultura.model.Criteria;
import com.cultura.model.LikeVO;

public interface BoardService {
	
	public void regist(BoardVO board)throws Exception;
	public BoardVO read(Integer board_id)throws Exception;
	public void update(BoardVO board)throws Exception;
	public void remove(Integer board_id)throws Exception;
	public List<BoardVO> listSearchCriteria(Criteria cri)throws Exception;
    public int listSearchCount(Criteria cri)throws Exception;
    public void registerLike(LikeVO vo) throws Exception;
    public void removeLike(LikeVO vo) throws Exception;
    public int checkLikedBoard(LikeVO vo)throws Exception;
}
