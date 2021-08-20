package com.sj.board.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class AttachmentDto implements java.io.Serializable{

    private Long fileId;
    private Long postsId;
    private Long userId;
    private String originName;
    private String saveName;
    private String filePath;
    private String fileExt;
    private Long fileSize;
    private Date fileRegDate;

}
