package com.example._oormthonUNIV.domain.post.controller;

import com.example._oormthonUNIV.domain.post.dto.RequestDto;
import com.example._oormthonUNIV.domain.post.dto.ResponseDto;
import com.example._oormthonUNIV.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Tag(name = "Post", description = "게시물 API")
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createPost(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestPart("post") RequestDto request,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        String username = userDetails.getUsername(); // 현재 로그인한 유저의 이름 가져오기
        return ResponseEntity.ok(postService.createPost(request, image, username));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/myposts")
    public ResponseEntity<ResponseDto> getPostById(@AuthenticationPrincipal UserDetails userDetails) {
        String username= userDetails.getUsername();
        return ResponseEntity.ok(postService.getPostByUsername(username));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String username = userDetails.getUsername(); // 현재 로그인한 유저의 이름 가져오기
        postService.deletePost(id, username);
        return ResponseEntity.ok("게시글 삭제 완료");
    }
}
