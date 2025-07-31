package org.example.springpracticeschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringPracticeScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPracticeScheduleApplication.class, args);
    }

}
