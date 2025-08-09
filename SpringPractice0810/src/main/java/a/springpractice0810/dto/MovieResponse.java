package a.springpractice0810.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MovieResponse {

    private final Long id;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public MovieResponse(Long id, String title, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.createdAt=createdAt;
        this.modifiedAt=modifiedAt;
    }
}
