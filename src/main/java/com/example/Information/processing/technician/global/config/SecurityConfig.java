package com.example.Information.processing.technician.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/actuator/health").permitAll()
                        .requestMatchers("/", "/index.html", "/css/**", "/js/**", "/images/**","/error/**").permitAll() // 메인 및 정적 리소스 허용
                        .requestMatchers("/user/signup", "/user/login").permitAll() // 회원가입, 로그인 페이지 접근 허용
                        .anyRequest().authenticated() // 그 외 요청은 인증 필요
                )
                .formLogin((form) -> form
                        .loginPage("/user/login") // 커스텀 로그인 페이지 경로
                        .loginProcessingUrl("/login") // 스프링 시큐리티가 로그인 처리를 하는 URL (HTML form action과 일치해야 함)
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/user/logout") // 로그아웃 URL 지정
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true) // 세션 날리기
                        .permitAll()
                );

        return http.build();
    }
}