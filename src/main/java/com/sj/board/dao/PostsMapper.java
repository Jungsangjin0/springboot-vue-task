package com.sj.board.dao;

import com.sj.board.dto.PostsDto;
import com.sj.board.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostsMapper {
    /* 전체,조건 조회 */
    List<PostsDto> findList(SearchDto search);

    /* 상세 조회 */
    PostsDto findById(@Param("postsId") Long postsId);

    /* 등록 */
    Long insertPost(PostsDto post);

    /* 삭제 */
    void deleteById(Long postsId);

    /* 수정 */
    void updateById(PostsDto posts);
}
