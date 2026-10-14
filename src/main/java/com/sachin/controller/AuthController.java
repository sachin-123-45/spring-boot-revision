
package com.sachin.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sachin.entity.AppUser;
import com.sachin.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(  @RequestBody AppUser user) {

        AppUser user1 = userService.register(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                        "id", user1.getId(),
                        "username", user1.getUsername(),
                        "message", "User registered successfully"
                ));
    }
}
