package ru.comfortsoft.value.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SearchConditionDto {
    @NotNull
    @Positive
    private final Integer number;
    @NotBlank
    private final String path;

    @Override
    public String toString() {
        return "SearchConditionDto{" +
                "number=" + number +
                ", path='" + path + '\'' +
                '}';
    }
}
