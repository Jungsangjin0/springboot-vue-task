package com.sj.board.controller;

import com.sj.board.dto.AttachmentDto;
import com.sj.board.dto.PostsDto;
import com.sj.board.dto.SearchDto;
import com.sj.board.file.FileStore;
import com.sj.board.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rest/api/v1")
@RequiredArgsConstructor
@Slf4j
public class PostsController {

    private final PostsService postsService;
    private final FileStore fileStore;

    /**
     * 게시물 조회 // 조건 조회
     * @param search 검색조건(keyword, startDate, endDate)
     * @return 게시물 리스트
     */
    @GetMapping("/posts")
    public List<PostsDto> postsList(@ModelAttribute SearchDto search) {

      return postsService.findList(search);
    }

    /**
     * 리스트 상세조회
     * @param postsId
     * @return
     */
    @GetMapping("/posts/{postsId}")
    public PostsDto posts(@PathVariable Long postsId) {

        return postsService.findById(postsId);
    }

    /**
     * 게시물 등록
     * @param posts title, content
     * @return
     */
    @PostMapping("/posts")
    public Long insertPosts(@ModelAttribute PostsDto posts, HttpSession session, @RequestParam(value = "file", required = false) List<MultipartFile> file) throws IOException {

        /* session 정보가져오기 */

//        MemberDto user = (MemberDto) session.getAttribute("user");

        /* 등록자 */
//        posts.setRegUserId(user.getUserId());
        posts.setRegUserId(1L);
        /* 수정자 */
//        posts.setModifyUserId(user.getUserId());
        posts.setModifyUserId(1L);


        if(file!= null && !file.isEmpty()){
            List<AttachmentDto> files = fileStore.storeFiles(file);
            for (AttachmentDto attachmentDto : files) {
                attachmentDto.setRegUserId(posts.getRegUserId());
                attachmentDto.setModifyUserId(posts.getModifyUserId());
            }
            posts.setFiles(files);
        }


        return postsService.insertPost(posts);

    }

    /**
     * 게시물 수정
     * @param postsId
     * @param posts
     * @return
     */
    @PutMapping("/posts/{postsId}")
    public String updatePosts(@PathVariable long postsId, @RequestBody PostsDto posts) {
        System.out.println("postsId = " + postsId);
        System.out.println("posts = " + posts);

        postsService.updateById(postsId, posts);

        return "updatePosts";
    }

    /**
     * 게시물 삭제
     * @param postsId
     * @return
     */
    @DeleteMapping("/posts/{postsId}")
    public Long deletePosts(@PathVariable Long postsId) {

        postsService.deleteById(postsId);

        return postsId;
    }
}
