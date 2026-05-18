package com.quiz.QuizHub.support;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FaqService {
    private final FaqRepository faqRepository;
    private final FaqMapper faqMapper;

    public List<FaqResponse> getAllFaq() {
        return faqRepository.findAll()
                .stream()
                .map(faqMapper::toDto)
                .toList();
    }

    public FaqResponse addFaq(FaqRequest request) {
        var faq = faqMapper.toEntity(request);
        return faqMapper.toDto(faqRepository.save(faq));
    }

    public FaqResponse updateFaq(@Valid FaqRequest request, Long faqId) {
        var faq = faqRepository.findById(faqId).orElse(null);
        if (faq == null) {throw new RuntimeException("invalid faqId");}
        faqMapper.update(request, faq);
        return faqMapper.toDto(faqRepository.save(faq));
    }

    public void deleteFaq(Long faqId) {
        faqRepository.deleteById(faqId);
        return;
    }
}
