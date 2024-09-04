package com.techlabs.controller;

import com.techlabs.entity.ImageModel;
import com.techlabs.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestPart("file") MultipartFile file,
                                      @RequestParam("name") String name) {
        try {
            ImageModel imageModel = new ImageModel();
            imageModel.setName(name);
            imageModel.setFile(file);
            return imageService.uploadImage(imageModel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
