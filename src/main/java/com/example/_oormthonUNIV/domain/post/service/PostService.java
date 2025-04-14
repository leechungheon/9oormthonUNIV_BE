package com.example._oormthonUNIV.domain.post.service;

import com.example._oormthonUNIV.domain.post.dto.RequestDto;
import com.example._oormthonUNIV.domain.post.dto.ResponseDto;
import com.example._oormthonUNIV.domain.post.entity.Post;
import com.example._oormthonUNIV.domain.post.repository.PostRepository;
import com.example._oormthonUNIV.domain.user.entity.User;
import com.example._oormthonUNIV.domain.user.repository.UserRepository;
import com.example._oormthonUNIV.global.s3.S3Uploader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;

    public PostService(PostRepository postRepository, UserRepository userRepository, S3Uploader s3Uploader) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.s3Uploader = s3Uploader;
    }

    public ResponseDto createPost(RequestDto requestDto, MultipartFile image, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String imageUrl = (image != null && !image.isEmpty()) ? s3Uploader.upload(image) : null;
        Post post= new Post(requestDto.getTitle(), requestDto.getContent(), imageUrl, user);
        Post savedPost=postRepository.save(post);

        return new ResponseDto(savedPost.getId(), savedPost.getTitle(), savedPost.getContent(), savedPost.getAuthor(),savedPost.getImageUrl());
    }

    @Transactional(readOnly = true)
    public List<ResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new ResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getImageUrl()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ResponseDto getPostByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return new ResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getImageUrl());
    }

    // Example method to delete a post by ID
    public void deletePost(Long id, String username) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized access");
        }
        postRepository.delete(post);
    }
}
