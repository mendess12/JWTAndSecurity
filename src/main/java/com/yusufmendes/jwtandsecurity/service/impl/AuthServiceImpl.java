package com.yusufmendes.jwtandsecurity.service.impl;

import com.yusufmendes.jwtandsecurity.dto.DtoUser;
import com.yusufmendes.jwtandsecurity.jwt.AuthRequest;
import com.yusufmendes.jwtandsecurity.jwt.AuthResponse;
import com.yusufmendes.jwtandsecurity.jwt.JwtService;
import com.yusufmendes.jwtandsecurity.model.User;
import com.yusufmendes.jwtandsecurity.repository.UserRepository;
import com.yusufmendes.jwtandsecurity.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            //girilern username ve password kontrolü yapılıyor
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
            authenticationProvider.authenticate(auth);
            //girilen bilgiler doğruysa token oluşturma

            Optional<User> user = userRepository.findByUsername(authRequest.getUsername());
            String token = jwtService.generateToken(user.get());

            return new AuthResponse(token);
        } catch (Exception e) {
            System.out.println("Kullanıcı adı veya şifre hatalı");
        }
        return null;
    }

    @Override
    public DtoUser register(AuthRequest authRequest) {

        DtoUser dtoUser = new DtoUser();
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));

        User savedUser = userRepository.save(user);
        BeanUtils.copyProperties(savedUser, dtoUser);
        return dtoUser;
    }
}
