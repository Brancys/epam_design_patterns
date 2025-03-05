package com.epam.rd.autocode.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Iterators {

    private Iterators() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array) {
        return new RepeatingIterator(array, 2);
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new RepeatingIterator(array, 3);
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new RepeatingIterator(array, 5);
    }

    public static Iterable<String> table(String[] columns, int[] rows) {
        return () -> new Iterator<String>() {
            private int colIndex = 0;
            private int rowIndex = 0;

            @Override
            public boolean hasNext() {
                return colIndex < columns.length;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                String cell = columns[colIndex] + rows[rowIndex];
                rowIndex++;
                if (rowIndex == rows.length) {
                    rowIndex = 0;
                    colIndex++;
                }
                return cell;
            }
        };
    }

    private static class RepeatingIterator implements Iterator<Integer> {
        private final int[] array;
        private final int repeatCount;
        private int index = 0;
        private int repeatIndex = 0;

        public RepeatingIterator(int[] array, int repeatCount) {
            this.array = array;
            this.repeatCount = repeatCount;
        }

        @Override
        public boolean hasNext() {
            return index < array.length;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int value = array[index];
            repeatIndex++;
            if (repeatIndex == repeatCount) {
                repeatIndex = 0;
                index++;
            }
            return value;
        }
    }
}