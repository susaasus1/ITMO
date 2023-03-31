package com.example.blps_lab1.service;

import com.example.blps_lab1.config.jwt.JwtUtils;
import com.example.blps_lab1.dto.request.SignInRequest;
import com.example.blps_lab1.dto.request.SignUpRequest;
import com.example.blps_lab1.exception.ResourceAlreadyExistException;
import com.example.blps_lab1.exception.ResourceNotFoundException;
import com.example.blps_lab1.model.ERole;
import com.example.blps_lab1.model.Jwt;
import com.example.blps_lab1.model.Role;
import com.example.blps_lab1.model.User;
import com.example.blps_lab1.repository.RoleRepository;
import com.example.blps_lab1.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(AuthenticationManager authenticationManager,
                       JwtUtils jwtUtils, UserRepository userRepository,
                       PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public Jwt authUser(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        signInRequest.getLogin(),
                        signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return new Jwt(jwt);
    }

    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Пользователь с логином " + login + " не существует"));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Пользователь с почтой " + email + " не существует"));
    }


    public boolean checkLoginOnExist(String login) {
        return userRepository.existsUserByLogin(login);
    }

    public boolean checkEmailOnExist(String email) {
        return userRepository.existsUserByEmail(email);
    }


    public User saveUser(SignUpRequest signUpRequest) {
        if (checkLoginOnExist(signUpRequest.getLogin()))
            throw new ResourceAlreadyExistException("Этот логин уже занят! Попробуйте другой");

        if (checkEmailOnExist(signUpRequest.getEmail()))
            throw new ResourceAlreadyExistException("Эта почта уже занята! Попробуйте другую");

        Set<Role> user_roles = new HashSet<>();
        Role userRole = roleRepository
                .findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new ResourceNotFoundException("Роль USER не найдена!"));
        user_roles.add(userRole);

        User user = new User(signUpRequest.getLogin(), passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail(), user_roles);

        userRepository.save(user);
        return user;
    }

}
