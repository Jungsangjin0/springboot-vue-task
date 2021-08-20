package com.sj.board.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CommentDto {

    private long commId;
    private long regUserId;
    private long modifyUserId;
    private long postsId;
    private String content;
    private Date regDate;
    private Date modifyDate;
    private String status;

}
