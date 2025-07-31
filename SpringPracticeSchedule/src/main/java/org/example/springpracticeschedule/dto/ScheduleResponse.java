package org.example.springpracticeschedule.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ScheduleResponse {

        private final Long id;
        private final String title;
        private final String content;
        private final String author;
        private final LocalDateTime createdAt;
        private final LocalDateTime modifiedAt;

        public ScheduleResponse(Long id, String title, String content, String author,LocalDateTime createdAt, LocalDateTime modifiedAt){
            this.id = id;
            this.title = title;
            this.content = content;
            this.author = author;
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
        }
}
