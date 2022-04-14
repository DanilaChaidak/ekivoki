package edu.javagroup.ekivoki.model;

import edu.javagroup.ekivoki.model.parent.NameEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question extends NameEntity {

    private String description;
}
