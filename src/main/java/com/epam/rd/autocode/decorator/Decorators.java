package com.epam.rd.autocode.decorator;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Decorators {

    private Decorators() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static <T> List<T> evenIndexElementsSubList(List<T> sourceList) {
        return new AbstractList<T>() {
            @Override
            public T get(int index) {
                if (index < 0 || index >= size()) {
                    throw new IndexOutOfBoundsException();
                }
                return sourceList.get(index * 2);
            }

            @Override
            public int size() {
                return (sourceList.size() + 1) / 2;
            }

            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    private int currentIndex = 0;

                    @Override
                    public boolean hasNext() {
                        return currentIndex * 2 < sourceList.size();
                    }

                    @Override
                    public T next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        T element = sourceList.get(currentIndex * 2);
                        currentIndex++;
                        return element;
                    }
                };
            }
        };
    }
}