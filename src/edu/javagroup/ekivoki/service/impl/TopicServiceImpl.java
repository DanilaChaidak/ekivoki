package edu.javagroup.ekivoki.service.impl;

import edu.javagroup.ekivoki.exeption.EntityNotFoundException;
import edu.javagroup.ekivoki.model.Topic;
import edu.javagroup.ekivoki.repository.dao.TopicRepository;
import edu.javagroup.ekivoki.repository.dao.impl.TopicRepositoryImpl;
import edu.javagroup.ekivoki.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    public TopicServiceImpl() {
        this.topicRepository = new TopicRepositoryImpl();
    }

    @Override
    public Topic findOne(Long id) {
        return topicRepository.findOne(id).orElseThrow(
                () -> new EntityNotFoundException("Item not found by id: " + id)
        );
    }

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic save(Topic topic) {
        return topic.getId() == null ?
                topicRepository.create(topic).orElseThrow(EntityNotFoundException::new) :
                topicRepository.update(topic).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void remove(Long id) {
        topicRepository.remove(id);
    }
}
