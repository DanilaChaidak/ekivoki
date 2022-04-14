package edu.javagroup.ekivoki.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionDto {

    private Long id;
    private String sessionUuid;
    private String dateCreation;
}
