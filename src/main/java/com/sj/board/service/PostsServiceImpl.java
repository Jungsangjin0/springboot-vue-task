package com.sj.board.service;

import com.sj.board.dao.AttachmentMapper;
import com.sj.board.dao.PostsMapper;
import com.sj.board.dto.AttachmentDto;
import com.sj.board.dto.PostsDto;
import com.sj.board.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService{

    private final PostsMapper postsMapper;
    private final AttachmentMapper attachmentMapper;

    /* 전체 조회/조건 조회 */
    @Override
    public List<PostsDto> findList(SearchDto search) {
        /* startDate endDate null 방지 */ //client
        if(search.getStartDate() != null && !search.getStartDate().equals("")) {
            if((search.getEndDate() == null || search.getEndDate().equals(""))) {
                String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                YearMonth month = YearMonth.from(LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                search.setEndDate(month.atEndOfMonth().toString());
            }
        }

        return postsMapper.findList(search);
    }

    /* 상세 조회 */
    @Override
    public PostsDto findById(Long postsId) {

        return postsMapper.findById(postsId);
    }

    /* 등록 */
    @Override
    @Transactional
    public Long insertPost(PostsDto posts) {

        /* insert post */
        postsMapper.insertPost(posts);

        if(posts.getFiles() != null && posts.getFiles().size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("list", posts.getFiles());
            map.put("postId", posts.getPostsId());
            try{
                attachmentMapper.insertFiles(map);
            }catch (Exception e) {
                for(int i = 0; i < posts.getFiles().size(); i++) {
                    new File(posts.getFiles().get(i).getFilePath()).delete();
                }
            }

        }

        return posts.getPostsId();
    }

    /* 삭제 */
    @Override
    @Transactional
    public List<AttachmentDto> deleteById(Long postsId) {

        /* 파일 삭제 로직 */
        /* 실패 시 오류 던져서 실패 만들기 */
        postsMapper.deleteById(postsId);
        List<AttachmentDto> files = attachmentMapper.findByPostsId(postsId);
        attachmentMapper.deleteByPostsId(postsId);

        return files;
    }

    /* 수정 */
    @Override
    public Long updateById(Long postsId, PostsDto posts) {

        posts.setPostsId(postsId);
        postsMapper.updateById(posts);

        return posts.getPostsId();
    }
}
