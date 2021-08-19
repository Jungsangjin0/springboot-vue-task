package com.sj.board.dao;

import com.sj.board.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    public CommentDto findById();
}
