package org.example.springpracticeuser.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpracticeuser.dto.UserRequest;
import org.example.springpracticeuser.dto.UserResponse;
import org.example.springpracticeuser.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(
            @RequestBody UserRequest userRequest
    ) {
        return ResponseEntity.ok(userService.save(userRequest));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.findUsers());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUser(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(userService.findUser(userId));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateUserName(
            @PathVariable Long userId,
            @RequestBody UserRequest userRequest
    ){
        return ResponseEntity.ok(userService.updateUserName(userId, userRequest));
    }

    @DeleteMapping("users/{userId}")
    public void deleteUser(
            @PathVariable Long userId
    ){
        userService.deleteUser(userId);
    }
}
