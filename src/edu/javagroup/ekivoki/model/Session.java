package edu.javagroup.ekivoki.model;

import edu.javagroup.ekivoki.model.parent.DateCreationEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Session extends DateCreationEntity {

    private String sessionUuid;
}
