public interface QuestionRepository {
    boolean save();
    Question[] getAll();
    Question getById(Integer id);
    boolean delete(Integer id);
}