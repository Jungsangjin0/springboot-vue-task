package com.sj.board.dao;

import com.sj.board.dto.PostsDto;
import com.sj.board.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostsMapper {

    List<PostsDto> findList(SearchDto search);
}
