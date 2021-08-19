package com.sj.board.service;

import com.sj.board.dao.PostsMapper;
import com.sj.board.dto.PostsDto;
import com.sj.board.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService{

    private final PostsMapper postsMapper;

    @Override
    public List<PostsDto> findList(SearchDto search) {

        return postsMapper.findList(search);
    }
}
