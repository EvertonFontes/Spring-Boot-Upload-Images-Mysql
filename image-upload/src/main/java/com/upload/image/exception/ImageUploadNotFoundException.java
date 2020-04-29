package com.upload.image.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageUploadNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ImageUploadNotFoundException(String message) {
        super(message);
    }  

    public ImageUploadNotFoundException(String message, Throwable cause) {
        super(message, cause);  
    }
	
}

