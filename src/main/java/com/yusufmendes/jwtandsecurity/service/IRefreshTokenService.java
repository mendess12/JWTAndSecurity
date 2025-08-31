package com.yusufmendes.jwtandsecurity.service;

import com.yusufmendes.jwtandsecurity.jwt.AuthResponse;
import com.yusufmendes.jwtandsecurity.jwt.RefreshTokenRequest;

public interface IRefreshTokenService {

    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
