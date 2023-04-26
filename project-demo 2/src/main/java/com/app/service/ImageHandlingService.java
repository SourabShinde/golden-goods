package com.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ProductDTO;

public interface ImageHandlingService {

	ProductDTO saveImage(long productId, MultipartFile imageFile) throws IOException;
	
	byte[] restoreImage(long productId) throws IOException;

}
