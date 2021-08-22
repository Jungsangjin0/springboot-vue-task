package com.sj.board.controller;

import com.sj.board.dto.AttachmentDto;
import com.sj.board.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/api/v1")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    /**
     * 파일 삭제
     * @param postsId
     * @param fileId
     * @return
     */
    @DeleteMapping("/posts/{postsId}/files/{fileId}")
    public String deleteFile(@PathVariable long postsId, @PathVariable long fileId) {

        attachmentService.deleteFile(postsId, fileId);

        return "deleteFile";
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
    public String addFiles(@PathVariable long postsId, @RequestParam(value = "files") List<MultipartFile> files) {

        System.out.println("files.size() = " + files.size());
        List<AttachmentDto> list = new ArrayList<>();
        String saveName = "";
        String ext = "";

        for(int i = 0; i < files.size(); i++) {
            ext = files.get(i).getOriginalFilename().substring(files.get(i).getOriginalFilename().lastIndexOf("."));
            saveName = UUID.randomUUID().toString().replace("-", "") + ext;

            AttachmentDto attachmentDto = AttachmentDto.builder()
                                                       .postsId(postsId)
                                                       .regUserId(1L)
                                                       .modifyUserId(1L)
                                                       .originName(files.get(i).getOriginalFilename())
                                                       .saveName(saveName)
                                                       .filePath("path")
                                                       .fileExt(ext)
                                                       .fileSize(files.get(i).getSize())
                                                        .build();
            list.add(attachmentDto);
            }

        attachmentService.addFiles(list);

        return "editFile";
    }
}
