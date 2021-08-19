package com.sj.board.dao;

import com.sj.board.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    public MemberDto findById(MemberDto member);

}
