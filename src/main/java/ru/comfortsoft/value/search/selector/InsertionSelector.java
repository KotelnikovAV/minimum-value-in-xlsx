package ru.comfortsoft.value.search.selector;

import org.springframework.stereotype.Component;

@Component("insertionSelector")
public class InsertionSelector implements Selector {

    @Override
    public int findNMinimumValue(int[] numbers, int N) {
        for (int i = 1; i < numbers.length; i++) {
            int key = numbers[i];
            int j = i - 1;

            while (j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j];
                j--;
            }
            numbers[j + 1] = key;
        }

        if (N < numbers.length) {
            return numbers[N - 1];
        } else {
            return numbers[numbers.length - 1];
        }
    }
}
