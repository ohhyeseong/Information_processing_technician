package com.example.Information.processing.technician.study.service;

import com.example.Information.processing.technician.study.domain.StudyNote;
import com.example.Information.processing.technician.study.dto.StudyNoteRequest;
import com.example.Information.processing.technician.study.dto.StudyNoteResponse;
import com.example.Information.processing.technician.study.repository.StudyNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyService {

    private final StudyNoteRepository studyNoteRepository;

    // 공부 기록 저장
    @Transactional
    public Long saveNote(StudyNoteRequest dto) {
        StudyNote note = StudyNote.builder()
                .title(dto.title())
                .content(dto.content())
                .category(dto.category())
                .build();
        return studyNoteRepository.save(note).getId();
    }

    // 전체 목록 조회 (최신순)
    public List<StudyNoteResponse> findAllNotes() {
        return studyNoteRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(StudyNoteResponse::from)// 엔티티 -> DTO 변환
                .collect(Collectors.toList());
    }

    // 상세 조회
    public StudyNoteResponse findNote(Long id) {
        StudyNote note = studyNoteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공부 기록이 없습니다. id="+id));
        return StudyNoteResponse.from(note);
    }

    // 수정 (Dirty ChecKing 활용)
    @Transactional
    public void updateNote(Long id, StudyNoteRequest request) {
        StudyNote note = studyNoteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공부 기록이 없습니다. id=" + id));

        note.update(request.title(), request.content(), request.category());
    }

    // 삭제
    @Transactional
    public void deleteNote(Long id) {
        StudyNote note = studyNoteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공부 기록이 없습니다. id=" + id));

        studyNoteRepository.delete(note);
    }

    // 메인 화면용: 최신 글 3개만 조회
    public List<StudyNoteResponse> findTop3Notes() {
        return studyNoteRepository.findAllByOrderByCreatedAtDesc().stream()
                .limit(3) // 3개만 제한
                .map(StudyNoteResponse::from)
                .collect(Collectors.toList());
    }
}
