package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.dto.FileDetails;
import com.onlinefoodcommercems.service.upload.AttachmentService;
import com.onlinefoodcommercems.utils.LinkUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping("/file/db/upload")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FileDetails upload(@RequestPart MultipartFile file) {
        try {
            var image = attachmentService.saves(file);
            return FileDetails.builder()
                    .error(false)
                    .filename(image.getFileName())
                    .link(LinkUtils.createImageLink(image.getFileName()))
                    .build();
        } catch (Exception e) {
            return FileDetails.builder().error(true).filename(file.getOriginalFilename()).build();
        }
    }

    @GetMapping("/file/db/{filename}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Resource> retrieve(@PathVariable String filename) throws IOException {
        var image = attachmentService.getImage(filename);
        var body = new ByteArrayResource(image.getData());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, image.getFileType())
                .cacheControl(CacheControl.maxAge(Duration.ofSeconds(60)).cachePrivate().mustRevalidate())
                .body(body);
    }
}
