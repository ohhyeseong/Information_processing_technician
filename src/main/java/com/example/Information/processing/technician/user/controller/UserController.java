package com.example.Information.processing.technician.user.controller;

import com.example.Information.processing.technician.user.dto.UserCreateRequest;
import com.example.Information.processing.technician.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("userCreateRequest", new UserCreateRequest(null, null, null));
        return "user/signup";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute UserCreateRequest request,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "user/signup";
        }

        try {
            userService.signup(request);
        } catch (IllegalArgumentException e) {
            // 중복 ID 등 예외 발생 시 에러 메시지 담아서 다시 폼으로
            bindingResult.reject("signupFailed", e.getMessage());
            return "user/signup";
        }
        return "redirect:/user/login"; // 가입 성공 시 로그인 페이지로
    }
}
