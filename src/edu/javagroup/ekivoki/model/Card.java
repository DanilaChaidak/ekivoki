package edu.javagroup.ekivoki.model;

import edu.javagroup.ekivoki.model.parent.LogEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card extends LogEntity {

    private Long topicId;
    private Long questionId;
    private int questionNumber;
    private String questionText;
    private int leadTime;
}
