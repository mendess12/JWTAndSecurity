package com.yusufmendes.jwtandsecurity.controller;

import com.yusufmendes.jwtandsecurity.dto.DtoEmployee;

public interface IRestEmployeeController {

    public DtoEmployee findEmployeeById(Long id);
}
