package com.yusufmendes.jwtandsecurity.controller;

import com.yusufmendes.jwtandsecurity.dto.DtoUser;
import com.yusufmendes.jwtandsecurity.jwt.AuthRequest;

public interface IRestAuthController {

    public DtoUser register(AuthRequest authRequest);
}
