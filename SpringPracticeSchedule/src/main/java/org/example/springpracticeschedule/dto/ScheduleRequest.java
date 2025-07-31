package org.example.springpracticeschedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequest {

    private String title;
    private String content;
    private String author;
    private String password;
}
