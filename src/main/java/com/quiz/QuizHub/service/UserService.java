package com.quiz.QuizHub.service;

import com.quiz.QuizHub.Repository.UserRepository;

import com.quiz.QuizHub.dto.UserRequest;
import com.quiz.QuizHub.dto.UserResponse;
import com.quiz.QuizHub.entity.Role;
import com.quiz.QuizHub.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    public final UserMapper userMapper;
    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    public List<UserResponse> getUser(){
        var user =  userRepository.findAll();
        return userMapper.toDto(user);
    }

    public UserResponse addUser(UserRequest request){
        if (userRepository.existsByEmail(request.getEmail())){
            return null;
        }

        var user = userMapper.toEntity(request);
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    public UserResponse updateUser(UserRequest request, Long id){
        var user = userRepository.findById(id).orElse(null);
        if(user == null) return null;
        userMapper.update(request, user);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        return;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));

        return new User(  // user in userDetailsPackage not User entity.
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

}

