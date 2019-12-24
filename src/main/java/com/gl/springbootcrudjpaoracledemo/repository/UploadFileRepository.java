package com.gl.springbootcrudjpaoracledemo.repository;

import com.gl.springbootcrudjpaoracledemo.request.UploadFileRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository extends JpaRepository<UploadFileRequest,String> {
}
