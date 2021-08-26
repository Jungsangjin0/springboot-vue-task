package com.sj.board.dto;

import lombok.*;

import java.sql.Date;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements java.io.Serializable{

    private Long commId;
    private Long regUserId;
    private Long modifyUserId;
    private Long postsId;
    private String content;
    private Date regDate;
    private Date modifyDate;
    private String status;

    private String modifyName;
    private String regName;
}