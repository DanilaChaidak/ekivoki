package edu.javagroup.ekivoki.service;

import edu.javagroup.ekivoki.model.Question;

import java.util.List;

public interface QuestionService {

    Question findOne(Long id);

    List<Question> findAll();

    Question save(Question question);

    void remove(Long id);
}
