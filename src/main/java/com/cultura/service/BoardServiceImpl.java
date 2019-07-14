package com.cultura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cultura.model.BoardVO;
import com.cultura.model.Criteria;
import com.cultura.model.LikeVO;
import com.cultura.persistence.BoardDAO;
import com.cultura.persistence.UserDAO;

@Service
public class BoardServiceImpl implements BoardService { 
    @Autowired
    private BoardDAO dao;
    
    @Override
    public void regist(BoardVO board) throws Exception {
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
    public void remove(Integer boardId) throws Exception {
        dao.delete(boardId);
    }
    
    @Autowired
    private UserDAO userDao;

    @Override
    public List<BoardVO> listSearchCriteria(Criteria cri) throws Exception { 
        if(cri.getSearchType() != null){
            // 닉네임 검색시 userId가 필요하다.
            if(cri.getSearchType().equals("nickname")){
                String userId = userDao.selectUserId(cri.getKeyword());
                cri.setKeyword(userId);
            }
        }
        return dao.listSearch(cri);
    }

    @Override
    public int listSearchCount(Criteria cri) throws Exception {
        if(cri.getSearchType() != null){
            // 닉네임 검색시 userId가 필요하다.
            if(cri.getSearchType().equals("nickname")){
                String userId = userDao.selectUserId(cri.getKeyword());
                cri.setKeyword(userId);
            }
        }   
        return dao.listSearchCount(cri);
    }  
    @Transactional
    @Override
    public void registerLike(LikeVO vo) throws Exception {
        dao.createLike(vo);
        dao.updateLikeCnt(vo.getBoardId(), 1);
    }   
    
    @Transactional
    @Override
    public void removeLike(LikeVO vo) throws Exception {
        dao.deleteLike(vo);
        dao.updateLikeCnt(vo.getBoardId(), -1);
    }

    @Override
    public int checkLikedBoard(LikeVO vo) throws Exception {
        return dao.readLikedBoard(vo);
    }
    
}
