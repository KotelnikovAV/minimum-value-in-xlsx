package ru.comfortsoft.value.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.comfortsoft.value.dto.SearchConditionDto;
import ru.comfortsoft.value.search.factory.SelectorFactory;
import ru.comfortsoft.value.search.reader.ValueReader;
import ru.comfortsoft.value.search.factory.ValueReaderFactory;
import ru.comfortsoft.value.search.selector.Selector;

@Service
@RequiredArgsConstructor
public class ValueServiceImpl implements ValueService {
    private final ValueReaderFactory readerFactory;
    private final SelectorFactory selectorFactory;

    @Override
    public int findMinimumValue(SearchConditionDto searchCondition) {
        String format = getFormat(searchCondition.getPath());
        ValueReader reader = readerFactory.getEngine(format);

        int[] numbers = reader.readValues(searchCondition.getPath());
        Selector selector = selectorFactory.getSelector(numbers.length);

        return selector.findNMinimumValue(numbers, searchCondition.getNumber());
    }

    private String getFormat(String path) {
        int lastDotIndex = path.lastIndexOf('.');
        return path.substring(lastDotIndex + 1).toLowerCase();
    }
}
