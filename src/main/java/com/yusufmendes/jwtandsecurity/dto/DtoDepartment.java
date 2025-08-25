package com.yusufmendes.jwtandsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoDepartment {

    private Long id;

    private String name;

    private String location;
}
