package repository;

import model.Question;

import java.util.List;

public interface QuestionRepository {
    boolean save(Question question);

    List<Question> getAll();

    Question getById(int id);

    boolean deleteById(int id);
}