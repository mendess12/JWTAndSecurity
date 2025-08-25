package com.yusufmendes.jwtandsecurity.service.impl;

import com.yusufmendes.jwtandsecurity.dto.DtoUser;
import com.yusufmendes.jwtandsecurity.jwt.AuthRequest;
import com.yusufmendes.jwtandsecurity.model.User;
import com.yusufmendes.jwtandsecurity.repository.UserRepository;
import com.yusufmendes.jwtandsecurity.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
