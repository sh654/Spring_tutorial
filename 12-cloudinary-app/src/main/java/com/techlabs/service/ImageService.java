package com.techlabs.service;

import com.techlabs.entity.ImageModel;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ImageService {
    ResponseEntity<Map<String, String>> uploadImage(ImageModel imageModel);
}
