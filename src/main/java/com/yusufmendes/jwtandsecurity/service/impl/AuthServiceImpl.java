package com.yusufmendes.jwtandsecurity.service.impl;

import com.yusufmendes.jwtandsecurity.dto.DtoUser;
import com.yusufmendes.jwtandsecurity.jwt.AuthRequest;
import com.yusufmendes.jwtandsecurity.jwt.AuthResponse;
import com.yusufmendes.jwtandsecurity.jwt.JwtService;
import com.yusufmendes.jwtandsecurity.model.RefreshToken;
import com.yusufmendes.jwtandsecurity.model.User;
import com.yusufmendes.jwtandsecurity.repository.RefreshTokenRepository;
import com.yusufmendes.jwtandsecurity.repository.UserRepository;
import com.yusufmendes.jwtandsecurity.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();
        //rondom bir token oluşturuldu
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        //refres token oluşturulma süresi ayarlandı(4 saat)
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshToken.setUser(user);

        return refreshToken;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            //girilern username ve password kontrolü yapılıyor
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
            authenticationProvider.authenticate(auth);
            //girilen bilgiler doğruysa token oluşturma

            Optional<User> user = userRepository.findByUsername(authRequest.getUsername());
            String accessToken = jwtService.generateToken(user.get());

            //refresh token kaydetme işlemi
            RefreshToken refreshToken = createRefreshToken(user.get());
            refreshTokenRepository.save(refreshToken);

            return new AuthResponse(accessToken, refreshToken.getRefreshToken());
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
