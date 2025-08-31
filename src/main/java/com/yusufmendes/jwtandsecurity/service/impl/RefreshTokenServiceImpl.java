package com.yusufmendes.jwtandsecurity.service.impl;

import com.yusufmendes.jwtandsecurity.jwt.AuthResponse;
import com.yusufmendes.jwtandsecurity.jwt.JwtService;
import com.yusufmendes.jwtandsecurity.jwt.RefreshTokenRequest;
import com.yusufmendes.jwtandsecurity.model.RefreshToken;
import com.yusufmendes.jwtandsecurity.model.User;
import com.yusufmendes.jwtandsecurity.repository.RefreshTokenRepository;
import com.yusufmendes.jwtandsecurity.service.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public boolean isRefreshTokenExpired(Date expiredDate) {
        return new Date().before(expiredDate);
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshToken.setUser(user);
        return refreshToken;
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(refreshTokenRequest.getRefreshToken());
        if (optional.isEmpty()) {
            System.out.println("Refresh token not found" + refreshTokenRequest.getRefreshToken());
        }

        RefreshToken refreshToken = optional.get();

        if (!isRefreshTokenExpired(refreshToken.getExpireDate())) {
            System.out.println("Refresh token expired" + refreshToken.getRefreshToken());
        }

        String accessToken = jwtService.generateToken(refreshToken.getUser());
        RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(refreshToken.getUser()));

        return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
    }
}
