package array;

import model.Option;

import java.io.Serializable;
import java.util.Arrays;

public class DynamicOptionArray implements Serializable {

    private Option[] options;

    public void add(Option option) {
        if (options == null) {
            options = new Option[1];
            options[0] = option;
        } else {
            int questionLength = options.length;
            Option[] tempOptions = new Option[questionLength + 1];

            for (int i = 0; i < questionLength; i++) {
                tempOptions[i] = options[i];
            }

            tempOptions[tempOptions.length - 1] = option;

            options = tempOptions;
        }
    }

    public void remove(int index) {
        if (index >= 0 && index < options.length) {
            options[index] = null;
        }
    }

    public Option get(int index) {
        if (index >= 0 && index < options.length) {
            return options[index];
        }
        return null;
    }

    public Option[] getAll() {
        return options;
    }

    @Override
    public String toString() {
        return " " + Arrays.toString(options);
    }
}