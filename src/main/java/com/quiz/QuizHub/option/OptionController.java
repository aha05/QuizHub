package com.quiz.QuizHub.option;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Option")
public class OptionController {
    private OptionService optionService;

    @GetMapping("question/{id}/options")
    public ResponseEntity<List<OptionResponse>> getOptions(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(optionService.getOptions(id));
    }

    @Operation(summary = "Add a option to the question")
    @PostMapping("question/{id}/options")
    public ResponseEntity<OptionResponse> addOptions(@PathVariable Long id, @Valid @RequestBody OptionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(optionService.addOption(id, request));
    }

    @PutMapping("question/{id}/option/{optionId}")
    public ResponseEntity<OptionResponse> updateOption(@PathVariable(name = "id") Long id, @PathVariable(name = "optionId") Long optionId, @Valid @RequestBody OptionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(optionService.updateOption(id, optionId, request));
    }

    @DeleteMapping("question/{id}/option/{optionId}")
    public ResponseEntity<Void> deleteOption(@PathVariable(name = "id") Long id, @PathVariable(name = "optionId") Long optionId) {
        optionService.removeOption(id, optionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
