package com.onlinefoodcommercems.service.upload;

import com.onlinefoodcommercems.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AttachmentService {
    Attachment saves(MultipartFile file) throws IOException;
    Attachment getImage(String filename) throws IOException;

}
