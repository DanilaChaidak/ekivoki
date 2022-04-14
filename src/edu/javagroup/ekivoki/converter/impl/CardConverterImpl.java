package edu.javagroup.ekivoki.converter.impl;

import edu.javagroup.ekivoki.converter.CardConverter;
import edu.javagroup.ekivoki.converter.QuestionConverter;
import edu.javagroup.ekivoki.converter.TopicConverter;
import edu.javagroup.ekivoki.dto.CardDto;
import edu.javagroup.ekivoki.model.Card;
import edu.javagroup.ekivoki.service.Impl.QuestionServiceImpl;
import edu.javagroup.ekivoki.service.Impl.TopicServiceImpl;
import edu.javagroup.ekivoki.service.QuestionService;
import edu.javagroup.ekivoki.service.TopicService;

public class CardConverterImpl implements CardConverter {

//    private final TopicConverter topicConverter;
//    private final QuestionConverter questionConverter;
//    private final TopicService topicService;
//    private final QuestionService questionService;
//
//    public CardConverterImpl() {
//        this.topicConverter = new TopicConverterImpl();
//        this.questionConverter = new QuestionConverterImpl();
//        this.topicService = new TopicServiceImpl();
//        this.questionService = new QuestionServiceImpl();
//    }


    @Override
    public CardDto convert(Card source) {
        if (source == null) {
            return null;
        }

        return null;
    }

    @Override
    public Card convert(CardDto source) {
        if (source == null) {
            return null;
        }

        return null;
    }
}
