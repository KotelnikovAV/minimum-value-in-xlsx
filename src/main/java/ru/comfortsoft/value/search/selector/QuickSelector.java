package ru.comfortsoft.value.search.selector;

import org.springframework.stereotype.Component;

@Component("quickSelector")
public class QuickSelector implements Selector {

    @Override
    public int findNMinimumValue(int[] numbers, int N) {
        return quickSelect(numbers, 0, numbers.length - 1, N - 1);
    }

    private int quickSelect(int[] numbers, int left, int right, int N) {
        if (left <= right) {
            int referenceIndex = medianOfThree(numbers, left, right);
            referenceIndex = partition(numbers, left, right, referenceIndex);

            if (referenceIndex == N) {
                return numbers[referenceIndex];
            } else if (referenceIndex < N) {
                return quickSelect(numbers, referenceIndex + 1, right, N);
            } else {
                return quickSelect(numbers, left, referenceIndex - 1, N);
            }
        }
        return numbers[numbers.length - 1];
    }

    private int medianOfThree(int[] numbers, int left, int right) {
        int mid = left + (right - left) / 2;

        if (numbers[right] < numbers[left]) swap(numbers, left, right);
        if (numbers[mid] < numbers[left]) swap(numbers, left, mid);
        if (numbers[right] < numbers[mid]) swap(numbers, mid, right);

        return mid;
    }


    private int partition(int[] numbers, int left, int right, int referenceIndex) {
        int reference = numbers[referenceIndex];
        swap(numbers, referenceIndex, right);
        int i = left;

        for (int j = left; j < right; j++) {
            if (numbers[j] < reference) {
                swap(numbers, i, j);
                i++;
            }
        }

        swap(numbers, i, right);
        return i;
    }

    private void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
