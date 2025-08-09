package a.springpractice0810.repository;

import a.springpractice0810.entity.Movie;
import a.springpractice0810.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMovie(Movie movie);

}
