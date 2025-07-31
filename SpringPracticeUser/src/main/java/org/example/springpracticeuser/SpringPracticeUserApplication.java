package org.example.springpracticeuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringPracticeUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPracticeUserApplication.class, args);
    }

}
