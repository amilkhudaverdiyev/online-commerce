package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface AttachmentRepository extends JpaRepository<Attachment, String> {

    @Query(value = "select * from attachment where file_name=:filename", nativeQuery = true)
    Optional<Attachment> findByFilename(String filename);
}
