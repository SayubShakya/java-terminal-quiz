package Demo;

import java.util.Arrays;

class Question {
    private static int idTracker = 1;
    private int id;
    private String questionText;
    private Option[] options;

    public Question(String questionText, Option[] options) {
        this.id = idTracker++;
        this.questionText = questionText;
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Option[] getOptions() {
        return options;
    }

    public boolean isCorrectAnswer(int answerIndex) {
        return options[answerIndex].isCorrect();
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + questionText + " | Options: " + Arrays.toString(options);
    }
}