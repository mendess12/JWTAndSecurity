package com.yusufmendes.jwtandsecurity.service.impl;

import com.yusufmendes.jwtandsecurity.dto.DtoDepartment;
import com.yusufmendes.jwtandsecurity.dto.DtoEmployee;
import com.yusufmendes.jwtandsecurity.model.Department;
import com.yusufmendes.jwtandsecurity.model.Employee;
import com.yusufmendes.jwtandsecurity.repository.EmployeeRepository;
import com.yusufmendes.jwtandsecurity.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DtoEmployee findEmployeeById(Long id) {

        DtoEmployee dtoEmployee = new DtoEmployee();
        DtoDepartment dtoDepartment = new DtoDepartment();
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isEmpty()) {
            //exception
            return null;
        }

        Employee employee = optional.get();
        Department department = employee.getDepartment();
        BeanUtils.copyProperties(employee, dtoEmployee);
        BeanUtils.copyProperties(department, dtoDepartment);

        dtoEmployee.setDepartment(dtoDepartment);

        return dtoEmployee;
    }
}
