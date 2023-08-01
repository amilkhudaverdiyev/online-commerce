package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.FileDetails;
import com.onlinefoodcommercems.service.upload.AttachmentService;
import com.onlinefoodcommercems.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        attachmentService.saves(file);
        return MessageUtils.getResponseEntity(ResponseMessage.ADD_SUCCESSFULLY, HttpStatus.OK);
    }

    @PostMapping("/file/db/upload")
    public FileDetails upload(@RequestPart MultipartFile file) {
        try {
            var image = attachmentService.saves(file);
            return FileDetails.builder()
                    .error(false)
                    .filename(image.getFileName())
                    .link(createImageLink(image.getFileName()))
                    .build();
        } catch (Exception e) {
            return FileDetails.builder().error(true).filename(file.getOriginalFilename()).build();
        }
    }

    private String createImageLink(String filename) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/file/db/" + filename)
                .toUriString();
    }

    @GetMapping("/file/db/{filename}")
    public ResponseEntity<Resource> retrieve(@PathVariable String filename) throws IOException {
        var image = attachmentService.getImage(filename);
        var body = new ByteArrayResource(image.getData());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, image.getFileType())
                .cacheControl(CacheControl.maxAge(Duration.ofSeconds(60)).cachePrivate().mustRevalidate())
                .body(body);
    }
}
