package com.sam.LoginSysSam.Service;

import com.sam.LoginSysSam.Auth.AuthResponse;
import com.sam.LoginSysSam.Auth.LoginRequest;
import com.sam.LoginSysSam.Auth.RegisterRequest;
import com.sam.LoginSysSam.Model.Role;


import com.sam.LoginSysSam.Model.User;
import com.sam.LoginSysSam.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUser_name(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUser_name()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUser_name())
                .password(passwordEncoder.encode( request.getPassword()))
                .first_name(request.getFirst_name())
                .last_name(request.getLast_name())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

}