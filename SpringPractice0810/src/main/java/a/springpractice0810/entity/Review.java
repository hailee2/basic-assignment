package a.springpractice0810.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.IdentityHashMap;

@Entity
@Getter
@NoArgsConstructor
public class Review extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    public Review(String content, Movie movie) {
        this.content = content;
        this.movie = movie;
    }

    public void updateReview(String content){
        this.content = content;
    }
}
