package ru.comfortsoft.value.search.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.comfortsoft.value.exception.UnsupportedFileFormatException;
import ru.comfortsoft.value.search.reader.ValueReader;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ValueReaderFactory {
    private final Map<String, ValueReader> readers;

    public ValueReader getReader(String format) {
        return Optional.ofNullable(readers.get(format + "ValueReader"))
                .orElseThrow(() -> new UnsupportedFileFormatException("Unsupported format: " + format));
    }
}
