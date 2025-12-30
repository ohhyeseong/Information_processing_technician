package com.example.Information.processing.technician.study.dto;

import com.example.Information.processing.technician.study.domain.StudyNote;

import java.time.LocalDateTime;

public record StudyNoteResponse(
        Long id,
        String title,
        String content,
        String category,
        LocalDateTime createdAt
) {
    // 엔티티를 DTO로 변환하는 정적 팩토리 메서드 (편의성)
    public static StudyNoteResponse from(StudyNote note) {
        return new StudyNoteResponse(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCategory(),
                note.getCreatedAt()
        );
    }
}
