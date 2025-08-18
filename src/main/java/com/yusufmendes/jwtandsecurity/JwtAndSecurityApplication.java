package com.yusufmendes.jwtandsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.yusufmendes"})
@EntityScan(basePackages = {"com.yusufmendes"})
@EnableJpaRepositories(basePackages = {"com.yusufmendes"})
@SpringBootApplication
public class JwtAndSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtAndSecurityApplication.class, args);
    }

}
