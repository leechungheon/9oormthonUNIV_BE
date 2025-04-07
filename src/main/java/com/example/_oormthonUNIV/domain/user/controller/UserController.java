package com.example._oormthonUNIV.domain.user.controller;
import com.example._oormthonUNIV.domain.user.entity.User;
import com.example._oormthonUNIV.domain.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("home")
    public String home() {
        return "home";
    }

    @PostMapping("join")
    public String join(@RequestBody User user) {
        return userService.register(user);
    }
}
