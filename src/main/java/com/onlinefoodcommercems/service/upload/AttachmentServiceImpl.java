package com.onlinefoodcommercems.service.upload;

import com.onlinefoodcommercems.entity.Attachment;
import com.onlinefoodcommercems.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;


    public Attachment saves(MultipartFile file) throws IOException {
        log.error("file " + file);
        var attachment = Attachment.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .data(file.getBytes())
                .build();
        log.error("attachment {}",attachment);
        return attachmentRepository.save(attachment);
    }

    @Override
    public Attachment getImage(String filename) throws IOException {
        log.error("file name " + filename);
        return attachmentRepository.findByFilename(filename)
                .orElseThrow(FileNotFoundException::new);
    }
}

