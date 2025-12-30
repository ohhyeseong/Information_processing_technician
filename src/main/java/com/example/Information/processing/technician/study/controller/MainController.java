package com.example.Information.processing.technician.study.controller;

import com.example.Information.processing.technician.study.dto.StudyNoteResponse;
import com.example.Information.processing.technician.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final StudyService studyService;

    @GetMapping("/")
    public String index(Model model) {
        // 최신 글 3개를 가져와서 모델에 담음
        List<StudyNoteResponse> recentNotes = studyService.findTop3Notes();
        model.addAttribute("recentNotes", recentNotes);

        return "index";
    }
}
