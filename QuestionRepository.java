public interface QuestionRepository {
    boolean save(Question question);
    Question[] getAll();
    Question[] getById(int id);
    boolean deleteById(int id);
}