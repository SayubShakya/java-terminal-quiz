package Demo;

public interface QuestionRepository {
    boolean save(Question question);
    Question[] getAll();
    Question getById(Integer id);
    boolean delete(Integer id);
}

