package com.sj.board.service;

import com.sj.board.dao.MemberMapper;
import com.sj.board.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public MemberDto finById(MemberDto user) {

        return memberMapper.findById(user);
    }
}
