package com.techlabs.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageModel {
    private String name;
    private MultipartFile file;
}
