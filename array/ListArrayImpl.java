package array;

import java.io.Serializable;
import java.util.Arrays;

public class ListArrayImpl<T> implements List<T>, Serializable {

    private Object[] items;

    public void add(T item) {
        if (items == null) {
            items = new Object[1];
            items[0] = item;
        } else {
            int questionLength = items.length;
            Object[] tempQuestions = new Object[questionLength + 1];

            for (int i = 0; i < questionLength; i++) {
                tempQuestions[i] = items[i];
            }

            tempQuestions[tempQuestions.length - 1] = item;

            items = tempQuestions;
        }
    }

    public void remove(int index) {
        if (index >= 0 && index < items.length) {
            items[index] = null;
        }
    }

    public T get(int index) {
        if (index >= 0 && index < items.length) {
            return (T) items[index];
        }
        return null;
    }

    public T[] getAll() {
        return (T[]) items;
    }

    @Override
    public String toString() {
        return "DynamicQuestionArray{" +
                "questions=" + Arrays.toString(items) +
                '}';
    }
}
