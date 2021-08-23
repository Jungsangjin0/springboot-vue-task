package com.sj.board.controller;

import com.sj.board.dao.CommentMapper;
import com.sj.board.dto.CommentDto;
import com.sj.board.dto.MemberDto;
import com.sj.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping("/rest/api/v1")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    /**
     * 댓글 등록
     *
     * @param postsId
     * @param comment
     * @return
     */
    @PostMapping("/posts/{postsId}/comm")
    public long insertComment(@PathVariable long postsId, @RequestBody CommentDto comment, HttpSession session) {
//        MemberDto member = (MemberDto) session.getAttribute("user");
//        Long userId = member.getUserId();

        comment.setPostsId(postsId);
        comment.setModifyUserId(1L);
        comment.setRegUserId(1L);

        long number = commentService.insert(comment);

        return number;
    }

    /**
     * 댓글 수정
     *
     * @param postsId
     * @param commId
     * @return
     */
    @PutMapping("/posts/{postsId}/comm/{commId}")
    public Long updateComment(@PathVariable long postsId, @PathVariable long commId, @RequestBody CommentDto comment, HttpSession session) {

        comment.setModifyUserId(1L);
        comment.setPostsId(postsId);
        comment.setCommId(commId);

        commentService.update(comment);

        return comment.getCommId();
    }

    /**
     * 댓글 삭제
     *
     * @param postsId
     * @param commId
     * @return
     */
    @DeleteMapping("/posts/{postsId}/comm/{commId}")
    public String deleteComment(@PathVariable long postsId, @PathVariable long commId) {
        commentService.delete(postsId, commId);
        return "deleteComment";
    }

}