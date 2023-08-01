package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface AttachmentRepository extends JpaRepository<Attachment, String> {

//    boolean existsByFilename(String fileName);

    @Query(value = "select file_name from attachment",nativeQuery = true)
    List<String> findAllFilenames();
    @Query(value = "select * from attachment where file_name=:filename",nativeQuery = true)
    Optional<Attachment> findByFilename(String filename);
}
