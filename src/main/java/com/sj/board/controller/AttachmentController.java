package com.sj.board.controller;

import com.sj.board.dto.AttachmentDto;
import com.sj.board.file.FileStore;
import com.sj.board.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
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
        System.out.println("postsId = " + postsId);
        System.out.println("fileId = " + fileId);
        System.out.println("파일 삭ㅈ ㅔ메소드입니다.~~~~~");
        /* db 파일 정보 삭제 및 path */
        String filePath = attachmentService.deleteFile(postsId, fileId);
        /* 파일 삭제  service layer */


        return fileId;
    }

    /**
     * 파일 다운로드
     * @param fileId
     * @return
     */
    @GetMapping("/files/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable long fileId) throws MalformedURLException {

        AttachmentDto file = attachmentService.findById(fileId);

        UrlResource resource = new UrlResource("file:" + file.getFilePath());
        System.out.println("file.getFilePath() = " + file.getFilePath());
        System.out.println("file.getOriginName() = " + file.getOriginName());


        String encodeUploadFileName = UriUtils.encode(file.getOriginName(), StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodeUploadFileName + "\"";


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }


    /**
     * 파일 추가
     * @param postsId
     * @param files
     * @return
     */
    @PostMapping("/posts/{postsId}/files")
    public List<AttachmentDto> addFiles(@PathVariable long postsId, @RequestParam(value = "files") List<MultipartFile> files, HttpSession session, HttpServletRequest request) throws IOException {

        String[] fileName = new String[files.size()];

        if(request.getHeader("User-Agent").contains("Mac")) {
            for (int i = 0; i < files.size(); i++) {
                fileName[i] = Normalizer.normalize(files.get(i).getOriginalFilename(), Normalizer.Form.NFC);
            }
        }

        List<AttachmentDto> list = null;

        if(files != null &&!files.isEmpty()) {
            list = fileStore.storeFiles(files);
            if(list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setRegUserId(1L);
                    list.get(i).setModifyUserId(1L);
                    list.get(i).setPostsId(postsId);
                    list.get(i).setOriginName(fileName[i]);
                }
            }
        }

        return attachmentService.addFiles(list, postsId);
    }
}
