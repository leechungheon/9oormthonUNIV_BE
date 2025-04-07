package com.example._oormthonUNIV.domain.user.service;

import com.example._oormthonUNIV.domain.user.entity.User;
import com.example._oormthonUNIV.domain.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @PostMapping("join")
    public String join(@RequestBody User user) {
        if(user.getUsername() == null || user.getPassword() == null) {
            return "아이디와 비밀번호를 입력해주세요";
        }
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "이미 존재하는 아이디입니다";
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return "회원가입완료";
    }

}
