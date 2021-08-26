package com.sj.board.service;

import com.sj.board.dto.AttachmentDto;
import com.sj.board.dto.PostsDto;
import com.sj.board.dto.SearchDto;

import java.util.List;

public interface PostsService {

    /*전체, 조건 조회*/
    List<PostsDto> findList(SearchDto search);

    /*상세 조회*/
    PostsDto findById(Long postsId);

    /* 등록 */
    Long insertPost(PostsDto posts);

    /* 삭제 */
    List<AttachmentDto> deleteById(Long postsId);

    /* 수정 */
    Long updateById(Long postsId, PostsDto posts);
}
