package com.quiz.QuizHub.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.addUser(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserRequest request, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
