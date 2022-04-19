package edu.javagroup.ekivoki.converter.impl;

import edu.javagroup.ekivoki.converter.CardConverter;
import edu.javagroup.ekivoki.converter.QuestionConverter;
import edu.javagroup.ekivoki.converter.TopicConverter;
import edu.javagroup.ekivoki.dto.CardDto;
import edu.javagroup.ekivoki.dto.QuestionDto;
import edu.javagroup.ekivoki.dto.TopicDto;
import edu.javagroup.ekivoki.model.Card;
import edu.javagroup.ekivoki.model.Question;
import edu.javagroup.ekivoki.model.Topic;
import edu.javagroup.ekivoki.service.Impl.QuestionServiceImpl;
import edu.javagroup.ekivoki.service.Impl.TopicServiceImpl;
import edu.javagroup.ekivoki.service.QuestionService;
import edu.javagroup.ekivoki.service.TopicService;

public class CardConverterImpl implements CardConverter {

    private final TopicConverter topicConverter;
    private final QuestionConverter questionConverter;
    private final TopicService topicService;
    private final QuestionService questionService;

    public CardConverterImpl() {
        this.topicConverter = new TopicConverterImpl();
        this.questionConverter = new QuestionConverterImpl();
        this.topicService = new TopicServiceImpl();
        this.questionService = new QuestionServiceImpl();
    }

    @Override
    public CardDto convert(Card source) {
        if (source == null) {
            return null;
        }
        Question question = questionService.findOne(source.getQuestionId());
        QuestionDto questionDto = questionConverter.convert(question);
        Topic topic = topicService.findOne(source.getTopicId());
        TopicDto topicDto = topicConverter.convert(topic);
        CardDto target = new CardDto();
        target.setId(source.getId());
        target.setTopic(topicDto);
        target.setQuestionId(questionDto);
        target.setNumber(source.getNumber());
        target.setQuestion(source.getQuestion());
        target.setLeadTime(source.getLeadTime());
        target.setDateCreation(DateUtils.getDate(source.getDateCreation()));
        target.setLastModified(DateUtils.getDate(source.getLastModified()));
        target.setVersion(source.getVersion());
        return target;
    }

    @Override
    public Card convert(CardDto source) {
        return null;
    }
}
