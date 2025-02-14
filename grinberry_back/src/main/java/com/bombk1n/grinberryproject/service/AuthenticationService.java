package com.bombk1n.grinberryproject.service;


import com.bombk1n.grinberryproject.exception.UserAlreadyExistException;
import com.bombk1n.grinberryproject.model.dto.AuthenticationResponse;
import com.bombk1n.grinberryproject.model.dto.LoginRequest;
import com.bombk1n.grinberryproject.model.dto.RegisterRequest;
import com.bombk1n.grinberryproject.model.entity.user.UserEntity;
import com.bombk1n.grinberryproject.model.entity.user.UserRole;
import com.bombk1n.grinberryproject.repository.UserRepository;
import com.bombk1n.grinberryproject.security.jwt.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request){
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("User already exists");
        }
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(UserRole.USER));
        userRepository.save(user);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(request.getUsername());
        loginRequest.setPassword(request.getPassword());

        return login(loginRequest);

    }

    public AuthenticationResponse login(LoginRequest request){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(token);
    }
}
