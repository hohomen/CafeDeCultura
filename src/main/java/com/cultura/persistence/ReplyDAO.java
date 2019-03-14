package org.zerock.persistence;

import java.util.List;

import org.zerock.domain.ReplyVO;

public interface ReplyDAO {
    public List<ReplyVO> list(Integer board_id) throws Exception;
    public void create(ReplyVO vo) throws Exception;
    public void update(ReplyVO vo) throws Exception;
    public void delete(Integer reply_id) throws Exception;
}
