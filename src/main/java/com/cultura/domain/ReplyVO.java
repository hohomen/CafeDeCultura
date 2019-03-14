package org.zerock.domain;

import java.util.Date;

public class ReplyVO {

        private Integer reply_id;
        private Integer board_id;
        private String content;
        private String replyer;
        private Date reg_date;
        private Date update_date;
        
        public Integer getReply_id() {
            return reply_id;
        }
        public void setReply_id(Integer reply_id) {
            this.reply_id = reply_id;
        }
        public Integer getBoard_id() {
            return board_id;
        }
        public void setBoard_id(Integer board_id) {
            this.board_id = board_id;
        }
        public String getContent() {
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }
        public String getReplyer() {
            return replyer;
        }
        public void setReplyer(String replyer) {
            this.replyer = replyer;
        }
        public Date getReg_date() {
            return reg_date;
        }
        public void setReg_date(Date reg_date) {
            this.reg_date = reg_date;
        }
        public Date getUpdate_date() {
            return update_date;
        }
        public void setUpdate_date(Date update_date) {
            this.update_date = update_date;
        }
        @Override
        public String toString() {
            return "ReplyVO [reply_id=" + reply_id + ", board_id=" + board_id + ", content=" + content + ", replyer="
                    + replyer + ", reg_date=" + reg_date + ", update_date=" + update_date + "]";
        }          
        
        
}
