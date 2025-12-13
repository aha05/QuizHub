package com.quiz.QuizHub.user;

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


    public com.quiz.QuizHub.user.User findUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public List<UserResponse> getUsers(){
        var user =  userRepository.findAll();
        return userMapper.toDto(user);
    }

    public UserResponse addUser(UserRequest request){
        if (userRepository.existsByEmail(request.getEmail())){
           throw new UserAlreadyExistException();
        }
        var user = userMapper.toEntity(request);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    public UserResponse updateUser(UserRequest request, Long userId){
        var user = findUserById(userId);
        userMapper.update(request, user);
        return userMapper.toDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));
        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

}

