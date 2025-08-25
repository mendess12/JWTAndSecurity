package com.yusufmendes.jwtandsecurity.service;

import com.yusufmendes.jwtandsecurity.dto.DtoEmployee;

public interface IEmployeeService {

    DtoEmployee findEmployeeById(Long id);
}
