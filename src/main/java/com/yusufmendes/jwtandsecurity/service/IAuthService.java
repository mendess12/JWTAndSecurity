package com.yusufmendes.jwtandsecurity.service;

import com.yusufmendes.jwtandsecurity.dto.DtoUser;
import com.yusufmendes.jwtandsecurity.jwt.AuthRequest;

public interface IAuthService {

    public DtoUser register(AuthRequest authRequest);
}
