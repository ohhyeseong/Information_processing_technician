package com.example.Information.processing.technician.study.controller;

import com.example.Information.processing.technician.study.dto.StudyNoteRequest;
import com.example.Information.processing.technician.study.dto.StudyNoteResponse;
import com.example.Information.processing.technician.study.service.StudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {

    private final StudyService studyService;

    // 목록 페이지
    @GetMapping("/list")
    public String list(Model model) {
        List<StudyNoteResponse> notes = studyService.findAllNotes();
        model.addAttribute("notes", notes);
        return "study/list";
    }

    // 작성 페이지 이동
    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("studyNoteRequest", new StudyNoteRequest(null, null, null));
        return "study/write";
    }

    // 작성 처리
    @PostMapping("/write")
    public String write(@Valid @ModelAttribute StudyNoteRequest request,
                        BindingResult bindingResult) {

        // 유효성 검사 실패 시 다시 작성 폼으로
        if(bindingResult.hasErrors()) {
            return "study/write";
        }
        studyService.saveNote(request);
        return "redirect:/study/list";
    }

    // 상세 조회 (테스트용으로 추가)
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        // 여기서 없는 ID를 넣으면 IllegalArgumentException 발생 -> GlobalExceptionHandler가 잡음
        StudyNoteResponse note = studyService.findNote(id);

        model.addAttribute("note", note);
        return "study/detail"; // 파일이 없어도 예외가 먼저 터지므로 테스트 가능
    }
}
