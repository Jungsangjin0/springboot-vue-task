package com.sj.board.dao;

import com.sj.board.dto.AttachmentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttachmentMapper {

    /* insert files */
    void insertFiles(Map<String, Object> map);

    /* 게시물 삭제 시 삭제 */
    void deleteByPostsId(@Param("postsId") Long postsId);

    /* 파일 삭제 */
    void deleteFile(Map<String, Object> map);

    /* 파일 검색 */
    AttachmentDto findById(@Param("fileId")long fileId);

    /* 파일 추가 */
    void addFiles(Map<String, Object> map);

}
