package com.movieflix.service;

import com.movieflix.entity.User;
import com.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User register(User user){
        return userRepository.save(user);
    }
}
