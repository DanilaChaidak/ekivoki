package edu.javagroup.ekivoki.converter;

import edu.javagroup.ekivoki.dto.TopicDto;
import edu.javagroup.ekivoki.model.Topic;

public interface TopicConverter {

    TopicDto convert(Topic source);

    Topic convert(TopicDto source);
}
