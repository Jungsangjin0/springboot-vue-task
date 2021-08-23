package com.sj.board.controller;

import com.sj.board.dto.AttachmentDto;
import com.sj.board.file.FileStore;
import com.sj.board.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/api/v1")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;
    private final FileStore fileStore;

    /**
     * 파일 삭제
     * @param postsId
     * @param fileId
     * @return
     */
    @DeleteMapping("/posts/{postsId}/files/{fileId}")
    public long deleteFile(@PathVariable long postsId, @PathVariable long fileId) {
        /* db 파일 정보 삭제 및 path */
        String filePath = attachmentService.deleteFile(postsId, fileId);
        /* 파일 삭제 */
        boolean check = fileStore.deleteFile(filePath);

        return fileId;
    }

    /**
     * 파일 다운로드
     * @param fileId
     * @return
     */
    @GetMapping("/files/{fileId}")
    public AttachmentDto downloadFile(@PathVariable long fileId) {

        AttachmentDto file = attachmentService.findById(fileId);

        return file;
    }

    /**
     * 파일 추가
     * @param postsId
     * @param files
     * @return
     */
    @PostMapping("/posts/{postsId}/files")
    public String addFiles(@PathVariable long postsId, @RequestParam(value = "files") List<MultipartFile> files, HttpSession session) throws IOException {

        List<AttachmentDto> list = null;

        if(files != null &&!files.isEmpty()) {
            list = fileStore.storeFiles(files);

            if(list != null && list.size() > 0) {
                for (AttachmentDto attachmentDto : list) {

                    attachmentDto.setRegUserId(1L);
                    attachmentDto.setModifyUserId(1L);
                    attachmentDto.setPostsId(postsId);
                }
            }
        }


        attachmentService.addFiles(list);

        return "editFile";
    }
}
