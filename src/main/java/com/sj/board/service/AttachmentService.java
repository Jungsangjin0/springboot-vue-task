package com.sj.board.service;

import com.sj.board.dto.AttachmentDto;

import java.util.List;

public interface AttachmentService {

    /* 파일 삭제 */
    String deleteFile(long postsId, long fileId);

    /* 파일 검색 */
    AttachmentDto findById(long fileId);

    /* 파일 등록 */
    List<AttachmentDto> addFiles(List<AttachmentDto> list, long postsId);
}
