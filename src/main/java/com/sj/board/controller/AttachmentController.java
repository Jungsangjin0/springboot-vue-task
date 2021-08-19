package com.sj.board.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/v1")
public class AttachmentController {

    /**
     * 파일 삭제
     * @param postsId
     * @param fileId
     * @return
     */
    @DeleteMapping("/posts/{postsId}/files/{fileId}")
    public String deleteFile(@PathVariable int postsId, @PathVariable int fileId) {

        return "deleteFile";
    }

    /**
     * 파일 다운로드
     * @param fileId
     * @return
     */
    @GetMapping("/files/{fileId}")
    public String downloadFile(@PathVariable int fileId) {

        return "downloadFile";
    }

    /**
     * 파일 추가
     * @param postsId
     * @return
     */
    @PostMapping("/posts/{postsId}/files")
    public String editFile(@PathVariable int postsId) {

        return "editFile";
    }
}
