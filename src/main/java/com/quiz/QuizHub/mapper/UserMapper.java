package com.quiz.QuizHub.mapper;

import com.quiz.QuizHub.dto.UserRequest;
import com.quiz.QuizHub.dto.UserResponse;
import com.quiz.QuizHub.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequest request);
    void update(UserRequest request, @MappingTarget User user);

    UserResponse toDto(User user);
    List<UserResponse> toDto(List<User> user);
}
