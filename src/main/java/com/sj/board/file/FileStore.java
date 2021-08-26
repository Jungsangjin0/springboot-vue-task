package com.sj.board.file;

import com.sj.board.dto.AttachmentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * file upload
 */
@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    /**
     * file fullPath
     * @param filename file saveName
     * @return dir + fileName
     */
    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    /**
     * 파일 리스트 업로드
     * @param multipartFiles 파일 리스트
     * @return 저장 후 저장한 정보 리스트 반환
     * @throws IOException transferTo exception
     */
    public List<AttachmentDto> storeFiles(List<MultipartFile> multipartFiles) throws IOException {

        List<AttachmentDto> storeFileResult = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()){
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    /**
     * 파일을 매개 변수로 받아 저장할 이름으로 변경 후 프로젝트 내 물리적 저장
     * @param multipartFile 파일
     * @return AttachmentDto 객체 {파일이름, 저장이름, 확장자, content-type, }
     * @throws IOException
     */
    public AttachmentDto storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) {
            return null;
        }
        String originFilename = multipartFile.getOriginalFilename();
        //image.png
        //서버에 저장하는 파일명
        String saveName = createSavename(originFilename);
        multipartFile.transferTo(new File(getFullPath(saveName)));

        AttachmentDto attachment = AttachmentDto.builder()
                                                .originName(originFilename)
                                                .saveName(saveName)
                                                .fileExt(extractExt(originFilename))
                                                .contentType(multipartFile.getContentType())
                                                .fileSize(multipartFile.getSize())
                                                .filePath(getFullPath(saveName))
                                                .build();

        return attachment;


    }

    private String createSavename(String originFilename) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originFilename);
        return uuid + ext;
    }

    private String extractExt(String originFilename) {
        int pos = originFilename.lastIndexOf(".");
        return originFilename.substring(pos);
    }


    public boolean deleteFile(String filePath) {
        File file = new File(filePath);

        if(!file.exists()) {
            return false;
        }

        return file.delete();
    }

    public boolean deleteFiles(List<AttachmentDto> files) {

        int cnt = 0;
        for (AttachmentDto file : files) {
            if(deleteFile(file.getFilePath())) {
                cnt++;
            }
        }
        if(cnt == files.size()) {
            return true;
        }
        return false;
    }

}
