package com.movieflix.controller;

import com.movieflix.config.TokenService;
import com.movieflix.controller.request.LoginRequest;
import com.movieflix.controller.request.UserRequest;
import com.movieflix.controller.response.LoginResponse;
import com.movieflix.entity.User;
import com.movieflix.exception.UsernameOrPasswordInvalid;
import com.movieflix.mapper.UserMapper;
import com.movieflix.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Operation(summary = "Register an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User was successfully registered")
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(UserRequest userRequest){
        userService.register(UserMapper.toUser(userRequest));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Authenticate a user with email and password",
            description = "Returns a JWT access token if the credentials are valid."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully authenticated. Returns a JWT access token.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid email or password.",
                    content = @Content
            )
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            UsernamePasswordAuthenticationToken userAndPass =
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
            Authentication authentication = authenticationManager.authenticate(userAndPass);

            User user = (User) authentication.getPrincipal();
            String token = tokenService.generateToken(user);

            LoginResponse accessToken = LoginResponse.builder()
                    .access_token(token)
                    .build();

            return ResponseEntity.ok(accessToken);
        } catch (BadCredentialsException error) {
            throw new UsernameOrPasswordInvalid("Invalid credentials");
        }
    }


}
