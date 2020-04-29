package com.upload.image.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.upload.image.exception.ImageUploadException;
import com.upload.image.exception.ImageUploadNotFoundException;
import com.upload.image.model.ImageUpload;
import com.upload.image.repository.ImageUploadRepository;

@Service
public class ImageUploadService {
 
	@Autowired
	private ImageUploadRepository imageUploadRepository;
	
	 public ImageUpload storeFile(MultipartFile file) {
	        // Normalize file name
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	                throw new ImageUploadException("Caminho contem erro " + fileName);
	            }

	            ImageUpload dbFile = new ImageUpload(fileName, file.getContentType(), file.getBytes());

	            return imageUploadRepository.save(dbFile);
	        } catch (IOException ex) {
	            throw new ImageUploadException("Não conseguiu fazer upload " + fileName + ". Tente de novo!", ex);
	        }
	    }

	 public ImageUpload getFile(String fileId) {
	        return imageUploadRepository.findById(fileId)
	                .orElseThrow(() -> new ImageUploadNotFoundException("Arquivo não encontrado " + fileId));  
	    }
}
