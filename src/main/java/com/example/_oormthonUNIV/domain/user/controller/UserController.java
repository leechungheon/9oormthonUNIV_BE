package com.example._oormthonUNIV.domain.user.controller;

import com.example._oormthonUNIV.domain.user.entity.User;
import com.example._oormthonUNIV.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "회원 API")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private static final String USER_REGISTRATION_TOPIC = "user-registration";

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/join")
    public String join(@RequestBody User user) {
        return userService.register(user);
    }
}