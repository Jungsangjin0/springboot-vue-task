package com.sj.board.service;

import com.sj.board.dao.AttachmentMapper;
import com.sj.board.dto.AttachmentDto;
import com.sj.board.file.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements  AttachmentService{

    private final AttachmentMapper attachmentMapper;
    /* file root */
    private final FileStore fileStore;

    /* 파일 조회 */
    @Override
    public List<AttachmentDto> findByPostsId(long postsId) {

        return attachmentMapper.findByPostsId(postsId);
    }

    /* 파일 삭제 */
    @Override
    public String deleteFile(long postsId, long fileId) {

        Map<String, Object> map = new HashMap<>();
        map.put("postsId", postsId);
        map.put("fileId", fileId);

        attachmentMapper.deleteFile(map);
        String filePath = (String)map.get("filePath");
        System.out.println("filePath = " + filePath);
        /* 물리적 파일 삭제 */
        boolean check = fileStore.deleteFile(filePath);

        return filePath;
    }

    /* 파일 검색 */
    @Override
    public AttachmentDto findById(long fileId) {

        return attachmentMapper.findById(fileId);
    }

    /* 파일 추가 등록 */
    @Override
    @Transactional
    public List<AttachmentDto> addFiles(List<AttachmentDto> list, long postsId) {

        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        try{
            attachmentMapper.addFiles(map);
        }catch (Exception e) {
            if (list.size() > 0) {
                for(int i = 0; i < list.size(); i++) {
                    new File(list.get(i).getFilePath()).delete();
                }
            }
        }

        return attachmentMapper.findByPostsId(postsId);


    }
}
