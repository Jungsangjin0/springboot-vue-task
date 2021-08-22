package com.sj.board.dao;

import com.sj.board.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface CommentMapper {

    public CommentDto findById();

    /* 댓글 등록 */
    void insert(CommentDto comment);

    /* 댓글 수정 */
    void update(CommentDto comment);

    /* 댓글 삭제 */
    void delete(Map<String, Object> map);

//    HashMap<String, Object> findByPostsId(long i);

    CommentDto findByPostsId(long i);
//    HashMap<String, Object> findByPostsId(long i);
}


