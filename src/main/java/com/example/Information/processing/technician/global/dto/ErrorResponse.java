package com.example.Information.processing.technician.global.dto;

public record ErrorResponse(
        String errorCode,
        String errorMessage
) {
    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code,message);
    }
}
