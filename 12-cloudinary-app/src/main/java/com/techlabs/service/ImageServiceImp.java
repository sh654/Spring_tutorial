package com.techlabs.service;

import com.techlabs.CloudinaryConfiguration;
import com.techlabs.entity.Image;
import com.techlabs.entity.ImageModel;
import com.techlabs.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ImageServiceImp implements ImageService {

	@Autowired
	private CloudinaryConfiguration cloudConfig;
	
    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ResponseEntity<Map<String, String>> uploadImage(ImageModel imageModel) {
        try {
            if (imageModel.getName().isEmpty() || imageModel.getFile().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid input"));
            }

            Image image = new Image();
            image.setName(imageModel.getName());
            image.setUrl(cloudinaryService.uploadFile(imageModel.getFile(), "folder_1"));

            if (image.getUrl() == null) {
                return ResponseEntity.status(500).body(Map.of("error", "File upload failed"));
            }

            imageRepository.save(image);
            return ResponseEntity.ok().body(Map.of("url", image.getUrl()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Internal server error"));
        }
    }
}
