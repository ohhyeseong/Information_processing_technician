package com.example.Information.processing.technician.global.exception;

import com.example.Information.processing.technician.global.dto.ErrorResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 모든 컨트롤러에서 발생하는 예외를 감지함.
public class GlobalExceptionHandler {
    
    // 1. 잘못된 요청 (예: 없는 글 조회) 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e, Model model) {
        // 에러 DTO 생성
        ErrorResponse errorResponse = ErrorResponse.of("BAD_REQUEST", e.getMessage());
        
        // 모델에 담아서 에러 페이지로 전달
        model.addAttribute("error", errorResponse);
        return "error/common"; // 공통 에러 페이지
    }

    // 2. 그 외 알 수 없는 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        e.printStackTrace(); // 서버 로그에는 남김
        ErrorResponse errorResponse = ErrorResponse.of("SERVER_ERROR", "알 수 없는 오류가 발생했습니다. 관리자에게 문의하세요.");
        model.addAttribute("error", errorResponse);
        return "error/common";
    }
}
