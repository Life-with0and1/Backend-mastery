package com.example.post.service;

import lombok.Getter;

@Getter
public class UploadResult {

    private final String url;
    private final String publicId;

    UploadResult(String url, String publicId){
        this.url = url;
        this.publicId = publicId;
    }
}
