package com.sj.board.service;

import com.sj.board.dao.AttachmentMapper;
import com.sj.board.dto.AttachmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sun.net.util.IPAddressUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements  AttachmentService{

    private final AttachmentMapper attachmentMapper;
    /* file root */

    /* 파일 삭제 */
    @Override
    public String deleteFile(long postsId, long fileId) {

        Map<String, Object> map = new HashMap<>();
        map.put("postsId", postsId);
        map.put("fileId", fileId);

        attachmentMapper.deleteFile(map);

        return (String)map.get("filePath");

    }

    /* 파일 검색 */
    @Override
    public AttachmentDto findById(long fileId) {

        return attachmentMapper.findById(fileId);
    }

    /* 파일 등록 */
    @Override
    public void addFiles(List<AttachmentDto> list) {

        Map<String, Object> map = new HashMap<>();
        map.put("list", list);

        attachmentMapper.addFiles(map);
    }
}
