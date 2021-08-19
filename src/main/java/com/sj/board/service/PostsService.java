package com.sj.board.service;

import com.sj.board.dto.PostsDto;
import com.sj.board.dto.SearchDto;

import java.util.List;

public interface PostsService {

    /*전체, 조건 조회*/
    List<PostsDto> findList(SearchDto search);
}
