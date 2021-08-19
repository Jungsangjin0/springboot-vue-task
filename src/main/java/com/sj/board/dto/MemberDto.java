package com.sj.board.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class MemberDto implements java.io.Serializable{

    private Long userId;
    private String email;
    private String pwd;
    private String name;
    private Date regDate;
    private Date modifyDate;
    private String status;

}
