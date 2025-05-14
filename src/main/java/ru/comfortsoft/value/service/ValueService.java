package ru.comfortsoft.value.service;

import ru.comfortsoft.value.dto.SearchConditionDto;

public interface ValueService {
    int findMinimumValue(SearchConditionDto searchCondition);
}
