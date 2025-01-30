public class DynamicOptionArray {

  private Option[] options;
  public int length;

  public void add(Option option) {
    if (options == null) {
      options = new Option[1];
      options[0] = option;
    } else {
      int optionLength = options.length;
      Option[] tempOptions = new Option[optionLength + 1];

      for (int i = 0; i < optionLength; i++) {
        tempOptions[i] = options[i];
      }

      tempOptions[tempOptions.length - 1] = option;

      options = tempOptions;
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

  public void remove(int index) {
    if (index >= 0 && index < options.length) {
    }
  }

}