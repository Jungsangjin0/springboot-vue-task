package com.sj.board.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class PostsDto implements java.io.Serializable{

    private Long postsId;
    private Long regUserId;
    private Long modifyUserId;
    private String title;
    private String content;
    private Date regDate;
    private Date modifyDate;
    private String status;
}
