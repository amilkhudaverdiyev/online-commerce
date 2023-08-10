package com.onlinefoodcommercems.service.upload;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.entity.Attachment;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.repository.AttachmentRepository;
import com.onlinefoodcommercems.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final CustomerRepository customerRepository;


    public Attachment saves(MultipartFile file) throws IOException {
        var attachment = Attachment.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .data(file.getBytes())
                .build();

        return attachmentRepository.save(attachment);
    }
    @Override
    public Attachment getImage(String filename) throws IOException {
        return attachmentRepository.findByFilename(filename)
                .orElseThrow(FileNotFoundException::new);
    }
    }

