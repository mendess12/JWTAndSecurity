package com.yusufmendes.jwtandsecurity.repository;

import com.yusufmendes.jwtandsecurity.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
