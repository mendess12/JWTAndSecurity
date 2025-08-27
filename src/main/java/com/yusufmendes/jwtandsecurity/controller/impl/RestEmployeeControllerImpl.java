package com.yusufmendes.jwtandsecurity.controller.impl;

import com.yusufmendes.jwtandsecurity.controller.IRestEmployeeController;
import com.yusufmendes.jwtandsecurity.dto.DtoEmployee;
import com.yusufmendes.jwtandsecurity.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class RestEmployeeControllerImpl implements IRestEmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    /*
     * id boş olamaz(notEmpty)
     * notEmpty aktif etmek için valid kullanıldı
     * */
    @GetMapping("/{id}")
    @Override
    public DtoEmployee findEmployeeById(@PathVariable(value = "id") Long id) {
        return employeeService.findEmployeeById(id);
    }
}
