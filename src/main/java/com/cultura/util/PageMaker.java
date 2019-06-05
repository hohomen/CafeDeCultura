package com.cultura.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.cultura.model.PageCriteria;
import com.cultura.model.SearchCriteria;

public class PageMaker {
    private int totalCount;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int displayPageNum = 10;
    private PageCriteria cri;

    public void setCri(PageCriteria cri) {
        this.cri = cri;
    }
    
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }
    
    private void calcData() {
        endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
        startPage = (endPage - displayPageNum) + 1;
        int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
        prev = startPage == 1 ? false : true;
        next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
    }
    
    public int getTotalCount() {
        return totalCount;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public boolean isNext() {
        return next;
    }

    public int getDisplayPageNum() {
        return displayPageNum;
    }

    public PageCriteria getCri() {
        return cri;
    }

    public String makeQuery(int page) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
                .queryParam("perPageNum", cri.getPerPageNum()).build();
        return uriComponents.toUriString();
    }
    
    public String makeSearch(int page){        
        if(((SearchCriteria)cri).getKeyword() != null){
            UriComponents uriComponents =
                    UriComponentsBuilder.newInstance()
                    .queryParam("page", page)
                    .queryParam("perPageNum", cri.getPerPageNum())
                    .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
                    .queryParam("keyword", ((SearchCriteria)cri).getKeyword())
                    .build()
                    .encode();
            return uriComponents.toUriString();
        }
        else{
            UriComponents uriComponents =
                    UriComponentsBuilder.newInstance()
                    .queryParam("page", page)
                    .queryParam("perPageNum", cri.getPerPageNum())                    
                    .build()
                    .encode();
            return uriComponents.toUriString();
        }                    
        
    } 
}
