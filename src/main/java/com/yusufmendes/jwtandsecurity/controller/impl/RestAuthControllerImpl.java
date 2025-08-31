package com.yusufmendes.jwtandsecurity.controller.impl;

import com.yusufmendes.jwtandsecurity.controller.IRestAuthController;
import com.yusufmendes.jwtandsecurity.dto.DtoUser;
import com.yusufmendes.jwtandsecurity.jwt.AuthRequest;
import com.yusufmendes.jwtandsecurity.jwt.AuthResponse;
import com.yusufmendes.jwtandsecurity.jwt.RefreshTokenRequest;
import com.yusufmendes.jwtandsecurity.service.IAuthService;
import com.yusufmendes.jwtandsecurity.service.IRefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private IRefreshTokenService refreshTokenService;

    @PostMapping("/register")
    @Override
    public DtoUser register(@Valid @RequestBody AuthRequest authRequest) {
        return authService.register(authRequest);
    }

    @PostMapping("/authenticate")
    @Override
    public AuthResponse authenticate(@Valid @RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }

    @PostMapping("/refreshToken")
    @Override
    public AuthResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.refreshToken(refreshTokenRequest);
    }
}
