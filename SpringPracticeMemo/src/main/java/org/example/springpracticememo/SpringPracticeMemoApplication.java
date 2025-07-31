package org.example.springpracticememo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringPracticeMemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPracticeMemoApplication.class, args);
    }

}
