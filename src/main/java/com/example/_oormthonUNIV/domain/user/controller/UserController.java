package com.example._oormthonUNIV.domain.user.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @GetMapping("home")
    public String home() {
        return "home";
    }
}
