package com.example.Information.processing.technician.study.dto;

import jakarta.validation.constraints.NotBlank;

public record StudyNoteRequest(
        @NotBlank(message = "제목은 필수 입니다.")
        String title,

        @NotBlank(message = "내용은 필수 입니다.")
        String content,

        @NotBlank(message = "카테고리는 필수 입니다.")
        String category
) {
}
