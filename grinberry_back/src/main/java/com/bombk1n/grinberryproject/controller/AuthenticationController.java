package com.bombk1n.grinberryproject.controller;


import com.bombk1n.grinberryproject.model.dto.AuthenticationResponse;
import com.bombk1n.grinberryproject.model.dto.LoginRequest;
import com.bombk1n.grinberryproject.model.dto.RegisterRequest;
import com.bombk1n.grinberryproject.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request){
        System.out.println("Register request: " + request);
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest request){
        System.out.println("Login request: " + request);
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
