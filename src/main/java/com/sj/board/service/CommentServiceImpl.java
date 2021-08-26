package com.sj.board.service;

import com.sj.board.dao.CommentMapper;
import com.sj.board.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    /* 댓글 조회 */
    @Override
    public List<CommentDto> selectList(long postsId) {

        return commentMapper.findByPostsId(postsId);
    }

    /* 댓글 등록 */
    @Override
    public long insert(CommentDto comment) {

        commentMapper.insert(comment);

        return comment.getCommId();
    }

    /* 댓긄 수정 */
    @Override
    public long update(CommentDto comment) {

        commentMapper.update(comment);

        return comment.getCommId();
    }

    /* 댓글 삭제 */
    @Override
    public void delete(long postsId, long commId) {

        Map<String, Object> map = new HashMap<>();
        map.put("postsId", postsId);
        map.put("commId", commId);

        commentMapper.delete(map);

    }
}
