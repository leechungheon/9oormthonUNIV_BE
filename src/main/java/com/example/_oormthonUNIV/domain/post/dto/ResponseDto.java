package com.example._oormthonUNIV.domain.post.dto;

import com.example._oormthonUNIV.domain.user.entity.User;
import lombok.Data;

@Data
public class ResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String imageUrl;

    public ResponseDto(Long id, String title, String content, User author, String imageUrl) {
    }
}
