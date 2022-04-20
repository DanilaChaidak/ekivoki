package edu.javagroup.ekivoki.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {

    private Long id;
    private TopicDto topicDto;
    private QuestionDto questionId;
    private int questionNumber;
    private String questionText;
    private int leadTime;
    private String dateCreation;
    private String lastModified;
    private Long version;
}
