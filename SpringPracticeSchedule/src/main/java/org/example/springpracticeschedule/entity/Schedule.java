package org.example.springpracticeschedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String content;
    private String password;

    public Schedule(String title, String author, String content, String password){
        this.title = title;
        this.author = author;
        this.content = content;
        this.password = password;
    }

    public void updateSchedule(String title,String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }


}
