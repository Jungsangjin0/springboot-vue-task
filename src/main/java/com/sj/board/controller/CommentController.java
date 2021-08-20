package com.sj.board.controller;

import com.sj.board.dto.CommentDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/v1")
public class CommentController {

    @PostMapping("/posts/{postsId}/comm")
    public String insertComment(@PathVariable long postsId, @RequestBody CommentDto comment) {

        return "댓글 등록";
    }

    @PutMapping("/posts/{postsId}/comm/{commId}")
    public String updateComment(@PathVariable long postsid, @PathVariable long commId) {
//
        return "updatecomment";
    }

    @DeleteMapping("/posts/{postsId}/comm/{commId}")
    public String deleteComment(@PathVariable long postsId, @PathVariable long commId) {

        return "deleteComment";
    }
}
