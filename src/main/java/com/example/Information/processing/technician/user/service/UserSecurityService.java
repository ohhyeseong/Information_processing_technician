package com.example.Information.processing.technician.user.service;

import com.example.Information.processing.technician.user.domain.User;
import com.example.Information.processing.technician.user.domain.UserRole;
import com.example.Information.processing.technician.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService { // 1. UserDetailsService 인터페이스를 구현해야 함

    private final UserRepository userRepository;

    // 2. 스프링 시큐리티가 로그인 요청 시 자동으로 호출하는 메서드
    // "username(로그인 ID)"을 줄 테니, 해당하는 사용자 정보를 찾아서 리턴해달라는 뜻
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 3. DB에서 회원 조회
        Optional<User> _user = userRepository.findByUsername(username);
        if (_user.isEmpty()) {
            // 4. 회원이 없으면 예외 발생 (시큐리티가 알아서 로그인 실패 처리함)
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        User user = _user.get();

        // 5. 권한(Role) 부여 로직
        List<GrantedAuthority> authorities = new ArrayList<>();

        // (예시 로직) 아이디가 'admin'이면 관리자 권한, 아니면 일반 사용자 권한 부여
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }

        // 6. 스프링 시큐리티가 이해할 수 있는 User 객체(UserDetails 구현체)로 변환해서 반환
        // 여기서 반환된 객체의 비밀번호와 사용자가 입력한 비밀번호를 시큐리티가 비교함
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}