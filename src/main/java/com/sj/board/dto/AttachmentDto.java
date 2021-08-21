package com.sj.board.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class AttachmentDto implements java.io.Serializable{

    private long fileId;
    private long postsId;
    private long regUserId;
    private long modifyUserId;
    private String originName;
    private String saveName;
    private String filePath;
    private String fileExt;
    private long fileSize;
    private Date fileRegDate;
    private Date fileModifyDate;
    private String status;
}
