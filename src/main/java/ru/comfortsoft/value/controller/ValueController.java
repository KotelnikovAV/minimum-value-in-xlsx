package ru.comfortsoft.value.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.comfortsoft.value.dto.SearchConditionDto;
import ru.comfortsoft.value.service.ValueService;

@RestController
@RequestMapping("/value")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Search API", description = "Поиск N-ного минимального значения")
public class ValueController {
    private final ValueService valueService;

    @GetMapping("/minimum")
    @Operation(summary = "Найти N-ное минимальное значение")
    public int findMinimumValue(@ModelAttribute
                                @Valid
                                @Parameter(description = "DTO с условиями поиска")
                                SearchConditionDto searchCondition) {
        log.info("Получен GET запрос по адресу /value/minimum. Условия поиска: {}", searchCondition);
        return valueService.findMinimumValue(searchCondition);
    }
}
