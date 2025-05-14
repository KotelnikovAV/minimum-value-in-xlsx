package ru.comfortsoft.value.search.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.comfortsoft.value.search.selector.Selector;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SelectorFactory {
    private final Map<String, Selector> selectors;
    @Value("${value.SORT_THRESHOLD}")
    private static int SORT_THRESHOLD;

    public Selector getSelector(int size) {
        if (size <= SORT_THRESHOLD) {
            return selectors.get("insertionSelector");
        } else {
            return selectors.get("quickSelector");
        }
    }
}
