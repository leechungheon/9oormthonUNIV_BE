package com.example._oormthonUNIV.domain.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String roles;

    public List<String> getRoles() {
        if (roles == null || roles.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(roles.split(","));
    }
}
