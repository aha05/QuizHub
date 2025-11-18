package com.quiz.QuizHub.service;

import com.quiz.QuizHub.Repository.UserRepository;

import com.quiz.QuizHub.dto.UserRequest;
import com.quiz.QuizHub.dto.UserResponse;
import com.quiz.QuizHub.entity.User;
import com.quiz.QuizHub.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    public final UserMapper userMapper;
    public final UserRepository userRepository;

    public List<UserResponse> getUser(){
        var user =  userRepository.findAll();
        return userMapper.toDto(user);
    }

    public User addUser(UserRequest request){
        var user = userMapper.toEntity(request);
        return userRepository.save(user);
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
}
