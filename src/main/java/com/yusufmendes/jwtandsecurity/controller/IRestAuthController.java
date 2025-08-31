package com.yusufmendes.jwtandsecurity.controller;

import com.yusufmendes.jwtandsecurity.dto.DtoUser;
import com.yusufmendes.jwtandsecurity.jwt.AuthRequest;
import com.yusufmendes.jwtandsecurity.jwt.AuthResponse;
import com.yusufmendes.jwtandsecurity.jwt.RefreshTokenRequest;

public interface IRestAuthController {

    public DtoUser register(AuthRequest authRequest);

    public AuthResponse authenticate(AuthRequest authRequest);

    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
