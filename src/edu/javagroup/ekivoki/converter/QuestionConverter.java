package edu.javagroup.ekivoki.converter;

import edu.javagroup.ekivoki.dto.QuestionDto;
import edu.javagroup.ekivoki.model.Question;

public interface QuestionConverter {

    QuestionDto convert(Question source);

    Question convert(QuestionDto source);
}
