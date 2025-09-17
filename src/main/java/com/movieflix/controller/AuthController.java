package com.movieflix.controller;

import com.movieflix.controller.request.UserRequest;
import com.movieflix.controller.response.UserResponse;
import com.movieflix.mapper.UserMapper;
import com.movieflix.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @Operation(summary = "Register an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User was successfully registered")
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(UserRequest userRequest){
        userService.register(UserMapper.toUser(userRequest));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
