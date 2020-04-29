package com.upload.image.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.upload.image.model.ImageUpload;
import com.upload.image.model.ImageUploadResponse;
import com.upload.image.service.ImageUploadService;

@RestController
@RequestMapping("/images")
public class ImageUploadController {
 
	@Autowired
	private ImageUploadService imageService;
	

    @PostMapping("/uploadFile")
    public ImageUploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
    	ImageUpload fileName = imageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .toUriString();

        return new ImageUploadResponse(fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<ImageUploadResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());  
    }
    
    @GetMapping("/downloadFile/{fileName:.+}") 
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
    	ImageUpload databaseFile = imageService.getFile(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
                .body(new ByteArrayResource(databaseFile.getData()));    
    }

	
}
