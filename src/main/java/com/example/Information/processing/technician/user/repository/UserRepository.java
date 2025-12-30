package com.example.Information.processing.technician.user.repository;

import com.example.Information.processing.technician.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    // 로그인 ID로 회원 찾기
    Optional<User> findByUsername(String username);

    // 중복 가입 방지용
    boolean existsByUsername(String username);
}
