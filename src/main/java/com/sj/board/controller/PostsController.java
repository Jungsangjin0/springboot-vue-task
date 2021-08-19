package com.sj.board.controller;

import com.sj.board.dto.PostsDto;
import com.sj.board.dto.SearchDto;
import com.sj.board.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/v1")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    /**
     * 게시물 조회 // 조건 조회
     * @param search 검색조건(keyword, startDate, endDate)
     * @return 게시물 리스트
     */
    @GetMapping("/posts")
    public List<PostsDto> postsList(@RequestParam(required = false) SearchDto search) {

      return postsService.findList(search);
    }

    /**
     * 리스트 상세조회
     * @param postsId
     * @return
     */
    @GetMapping("/posts/{postsId}")
    public String posts(@PathVariable int postsId) {

        return "상세조회";
    }

    /**
     * 게시물 등록
     * @param posts
     * @return
     */
    @PostMapping("/posts")
    public String insertPosts(@RequestBody PostsDto posts) {

        return "insertPosts";
    }

    /**
     * 게시물 수정
     * @param postsId
     * @param posts
     * @return
     */
    @PutMapping("/posts/{postsId}")
    public String updatePosts(@PathVariable int postsId, @RequestBody PostsDto posts) {

        return "updatePosts";
    }

    /**
     * 게시물 삭제
     * @param postsId
     * @return
     */
    @DeleteMapping("/posts/{postsId}")
    public String deletePosts(@PathVariable int postsId) {

        return "deletePosts";
    }
}
