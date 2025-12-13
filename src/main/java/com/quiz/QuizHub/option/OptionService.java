package com.quiz.QuizHub.option;

import com.quiz.QuizHub.question.Question;
import com.quiz.QuizHub.question.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OptionService {
    public final OptionRepository optionRepository;
    private final OptionMapper optionMapper;
    private QuestionService questionService;

    public List<OptionResponse> getOptions(Long questionId) {
        var question = questionService.findQuestionById(questionId);

        var options = question.getOptions();
        if (options == null) throw new OptionNotFoundException("No option found.");
        return optionMapper.toDto(options);
    }

    public OptionResponse addOption(Long questionId, OptionRequest request) {
        Question question = questionService.findQuestionById(questionId);

        Option option = optionMapper.toEntity(request);
        option.setQuestion(question);

        return optionMapper.toDto(optionRepository.save(option));
    }

    public OptionResponse updateOption(Long questionId, Long optionId, OptionRequest request) {
        var option = optionRepository.findById(optionId).orElseThrow(OptionNotFoundException::new);
        optionMapper.update(request, option);
        return optionMapper.toDto(option);
    }

    public void removeOption(Long questionId, Long optionId) {
        optionRepository.deleteById(optionId);
    }

    public Option findOptionById(Long optionId){
        return optionRepository.findById(optionId)
                .orElseThrow(() -> new OptionNotFoundException("Option not found with id: " + optionId));
    }

    public void validateBelongsToQuestion(Long optionId, Long questionId) {
        if (!optionRepository.existsByIdAndQuestion_Id(optionId, questionId)) {
            throw new OptionNotInQuestionException(
                    "Option does not belong to the specified question"
            );
        }
    }

}
