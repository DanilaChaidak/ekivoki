package edu.javagroup.ekivoki.service.impl;

import edu.javagroup.ekivoki.exeption.EntityNotFoundException;
import edu.javagroup.ekivoki.model.Question;
import edu.javagroup.ekivoki.repository.dao.QuestionRepository;
import edu.javagroup.ekivoki.repository.dao.impl.QuestionRepositoryImpl;
import edu.javagroup.ekivoki.service.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl() {
        this.questionRepository = new QuestionRepositoryImpl();
    }

    @Override
    public Question findOne(Long id) {
        return questionRepository.findOne(id).orElseThrow(
                () -> new EntityNotFoundException("Question not found by id: " + id)
        );
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question save(Question question) {
        return question.getId() == null ?
                questionRepository.create(question).orElseThrow(EntityNotFoundException::new) :
                questionRepository.update(question).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void remove(Long id) {
        questionRepository.remove(id);
    }
}
