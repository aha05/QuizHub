package com.quiz.QuizHub.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("users")
@Tag(name = "User")
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

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody ChangePasswordRequest request){
        userService.changePassword(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/profile")
    public ResponseEntity<UserResponse> updateProfile(@Valid @RequestBody UserUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateProfile(request));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<UserResponse> updateUserStatus(@Valid @RequestBody StatusUpdateRequest request, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserStatus(request, id));
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<UserResponse> updateUserRole(@Valid @RequestBody RoleUpdateRequest request, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserRole(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
