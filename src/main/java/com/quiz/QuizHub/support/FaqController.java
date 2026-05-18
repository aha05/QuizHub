package com.quiz.QuizHub.support;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("faq")
@AllArgsConstructor
public class FaqController {
    private final FaqService faqService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FaqResponse> getAllFaq() {
        return faqService.getAllFaq();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FaqResponse addFaq(@Valid @RequestBody FaqRequest request) {
        return faqService.addFaq(request);
    }

    @PutMapping("/{faqId}")
    @ResponseStatus(HttpStatus.OK)
    public FaqResponse updateFaq(@Valid @RequestBody FaqRequest request, @PathVariable(name = "faqId") Long faqId) {
        return faqService.updateFaq(request, faqId);
    }

    @DeleteMapping("/{faqId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFaq(@PathVariable(name = "faqId") Long faqId) {
        faqService.deleteFaq(faqId);
        return;
    }
}
