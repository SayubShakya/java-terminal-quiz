public class DynamicQuestionArray {

  private Question[] questions;

  public void add(Question question) {
    if (questions == null) {
      questions = new Question[1];
      questions[0] = question;
    } else {
      int questionLength = questions.length;
      Question[] tempQuestions = new Question[questionLength + 1];

      for (int i = 0; i < questionLength; i++) {
        tempQuestions[i] = questions[i];
      }

      tempQuestions[tempQuestions.length - 1] = question;

      questions = tempQuestions;
    }
  }

  public void remove(int index) {
    if (index >= 0 && index < questions.length) {
      questions[index] = null;
    }
  }

  public Question get(int index) {
    if (index >= 0 && index < questions.length) {
      return questions[index];
    }
    return null;
  }

  public Question[] getAll() {
    return questions;
  }

}
