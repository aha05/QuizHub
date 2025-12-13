package com.quiz.QuizHub.user;

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
