package com.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.ProductRepository;
import com.app.dto.ProductDTO;
import com.app.entities.Product;
import com.app.exception.ResourceNotFoundException;

import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ImageServiceImpl implements ImageHandlingService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Value("${file.upload.location}")//in goes the key out comes the value
	private String folderLocation;
	
	@PostConstruct
	public void init() {
		File folder=new File(folderLocation);
		if(!folder.exists()) {
			log.info(folderLocation);
			folder.mkdirs();
		}
		else
			log.info("folder already exist");
		
	}
	
	@Override
	public ProductDTO saveImage(long productId, MultipartFile imageFile) throws IOException  {
		Product product=productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("Invalid productId"));
		String path=folderLocation+File.separator+imageFile.getOriginalFilename();
		log.info("path{}",path);
		product.setProductImagePath(path);
		Files.copy(imageFile.getInputStream(),Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
		return mapper.map(product,ProductDTO.class);
	}

	@Override
	public byte[] restoreImage(long productId) throws IOException {
		Product product=productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("Invalid productId"));
		String path=product.getProductImagePath();
		if(path!=null)
			return Files.readAllBytes(Paths.get(path));
		throw new ResourceNotFoundException("Image is Not yet Assigned for product"+product.getProductName());
	}

}
