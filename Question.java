class Question {
    private static int idTracker = 1;
    private int id;
    private String questionText;
    private DynamicOptionArray options;

    public Question(String questionText, DynamicOptionArray options) {
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

    public DynamicOptionArray getOptions() {
        return options;
    }

    public boolean isCorrectAnswer(int answerIndex) {
        return options.get(answerIndex).isCorrect();
    }


    @Override
    public String toString() {
        return "Question [id=" + id + ", questionText=" + questionText + ", options=" + options + "]";
    }

    

}