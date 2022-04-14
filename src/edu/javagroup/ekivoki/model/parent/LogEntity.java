package edu.javagroup.ekivoki.model.parent;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class LogEntity extends DateCreationEntity {

    private Date lastModified;
    private Long version;
}
