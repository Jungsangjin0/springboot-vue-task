package com.sj.board.dao;

import com.sj.board.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {

    CommentDto findById();

    /* 댓글 등록 */
    void insert(CommentDto comment);

    /* 댓글 수정 */
    void update(CommentDto comment);

    /* 댓글 삭제 */
    void delete(Map<String, Object> map);


    List<CommentDto> findByPostsId(@Param("POSTS_ID") long postsId);
}


