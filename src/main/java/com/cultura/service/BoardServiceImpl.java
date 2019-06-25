package com.cultura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cultura.model.BoardVO;
import com.cultura.model.SearchCriteria;
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
    public void delete(Integer boardId) throws Exception {
        dao.delete(boardId);        
    }
    
    @Autowired
    private UserDAO userDao;

    @Override
    public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception { 
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
    public int listSearchCount(SearchCriteria cri) throws Exception {
        if(cri.getSearchType() != null){
            // 닉네임 검색시 userId가 필요하다.
            if(cri.getSearchType().equals("nickname")){
                String userId = userDao.selectUserId(cri.getKeyword());
                cri.setKeyword(userId);
            }
        }   
        return dao.listSearchCount(cri);
    }  

    
}
