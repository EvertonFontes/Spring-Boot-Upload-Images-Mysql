package com.upload.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upload.image.model.ImageUpload;

@Repository
public interface ImageUploadRepository extends JpaRepository<ImageUpload, String>{

}
