package com.example.Information.processing.technician.study.repository;

import com.example.Information.processing.technician.study.domain.StudyNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyNoteRepository extends JpaRepository<StudyNote, Long> {
    // 카테고리별로 모아보기 기능을 위해 추가
    List<StudyNote> findByCategory(String category);

    // 최신순으로 정렬해서 가져오기
    List<StudyNote> findAllByOrderByCreatedAtDesc();
}
