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
import edu.javagroup.ekivoki.service.QuestionService;
import edu.javagroup.ekivoki.service.TopicService;
import edu.javagroup.ekivoki.service.impl.QuestionServiceImpl;
import edu.javagroup.ekivoki.service.impl.TopicServiceImpl;
import edu.javagroup.ekivoki.util.DateUtils;

public class CardConverterImpl implements CardConverter {

    private final TopicConverter topicConverter;
    private final TopicService topicService;
    private final QuestionConverter questionConverter;
    private final QuestionService questionService;

    public CardConverterImpl() {
        this.topicConverter = new TopicConverterImpl();
        this.topicService = new TopicServiceImpl();
        this.questionConverter = new QuestionConverterImpl();
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
        target.setTopicDto(topicDto);
        target.setQuestionId(questionDto);
        target.setQuestionNumber(source.getQuestionNumber());
        target.setQuestionText(source.getQuestionText());
        target.setLeadTime(source.getLeadTime());
        target.setDateCreation(DateUtils.getDate(source.getDateCreation()));
        target.setLastModified(DateUtils.getDate(source.getLastModified()));
        target.setVersion(source.getVersion());
        return target;
    }

    @Override
    public Card convert(CardDto source) {
        if (source == null) {
            return null;
        }
        Question question = questionService.findOne(source.getId());
        QuestionDto questionDto = questionConverter.convert(question);
        Topic topic = topicService.findOne(source.getId());
        TopicDto topicDto = topicConverter.convert(topic);
        Card target = new Card();
        target.setId(source.getId());
        target.setTopicId(topic.getId());
        target.setQuestionId(question.getId());
        target.setQuestionNumber(source.getQuestionNumber());
        target.setQuestionText(source.getQuestionText());
        target.setLeadTime(source.getLeadTime());
        return target;
    }
}
