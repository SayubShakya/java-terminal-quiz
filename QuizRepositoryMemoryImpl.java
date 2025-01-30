public class QuizRepositoryMemoryImpl implements QuestionRepository {

    private DynamicQuestionArray questions = new DynamicQuestionArray();

    @Override
    public boolean save(Question question) {
        questions.add(question);
        return true;
    }

    @Override
    public Question[] getAll() {
        return questions.getAll();
    }

    @Override
    public Question getById(int id) {
        for (int i = 0; i < questions.getAll().length; i++) {
            Question question = questions.get(i);
            if (question.getId() == id) {
                return question;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        System.out.println("Not available");
        return false;
    }

}
