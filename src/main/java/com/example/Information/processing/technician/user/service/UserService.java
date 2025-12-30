package com.example.Information.processing.technician.user.service;

import com.example.Information.processing.technician.user.domain.User;
import com.example.Information.processing.technician.user.domain.UserRole;
import com.example.Information.processing.technician.user.dto.UserCreateRequest;
import com.example.Information.processing.technician.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signup(UserCreateRequest request) {
        // 중복 ID 검사
        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.password());

        // 회원 저장
        User user = User.builder()
                .username(request.username())
                .password(encodedPassword)
                .email(request.email())
                .role(UserRole.USER) // 기본 권한은 USER
                .build();

        return userRepository.save(user).getId();
    }
}
