package com.cultura.persistence;

import java.util.List;

import com.cultura.model.BoardVO;
import com.cultura.model.Criteria;
import com.cultura.model.LikeVO;

public interface BoardDAO {
	public void create(BoardVO vo)throws Exception;
	public BoardVO read(Integer boardId)throws Exception;
	public void update(BoardVO vo)throws Exception;
	public void delete(Integer boardId)throws Exception;	
	public List<BoardVO> listSearch(Criteria cri)throws Exception;
	public int listSearchCount(Criteria cri)throws Exception;
	public void updateReplyCnt(Integer boardId, int amount)throws Exception;
	public void updateViewCnt(Integer boardId)throws Exception;
	public void createLike(LikeVO vo)throws Exception;
	public void updateLikeCnt(Integer boardId, int amount)throws Exception;
	public void deleteLike(LikeVO vo)throws Exception;
	public int readLikedBoard(LikeVO vo)throws Exception;
}
