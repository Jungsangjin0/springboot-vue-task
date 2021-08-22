package com.sj.board.service;

import com.sj.board.dto.CommentDto;

public interface CommentService {

    /* 댓글 등록 */
    long insert(CommentDto comment);

    /* 댓글 수정 */
    long update(CommentDto comment);

    /* 댓글 삭제 */
    void delete(long postsId, long commId);
}
