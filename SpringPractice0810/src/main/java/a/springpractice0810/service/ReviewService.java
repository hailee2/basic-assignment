package a.springpractice0810.service;

import a.springpractice0810.dto.ReviewRequest;
import a.springpractice0810.dto.ReviewResponse;
import a.springpractice0810.entity.Movie;
import a.springpractice0810.entity.Review;
import a.springpractice0810.repository.MovieRepository;
import a.springpractice0810.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public ReviewResponse save(ReviewRequest request, Long movieId){
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("입력한 Movie가 존재하지 않음")
        );
        Review review = new Review(
                request.getContent(),
                movie
        );
        Review savedReview = reviewRepository.save(review);
        return new ReviewResponse(
                savedReview.getId(),
                savedReview.getContent(),
                savedReview.getCreatedAt(),
                savedReview.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> findAll(Long movieId){
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("movie is not exist")
        );

        List<Review> movies = reviewRepository.findAllByMovie(movie);
        List<ReviewResponse> dtos = new ArrayList<>();

        for (Review review : movies) {
            dtos.add(
                     new ReviewResponse(
                             review.getId(),
                             review.getContent(),
                             review.getCreatedAt(),
                             review.getModifiedAt()
                     )
            );
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public ReviewResponse findReview(Long movieId, Long reviewId){
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 리뷰입니다.")
        );
        if (!review.getMovie().getId().equals(movieId)) {
            throw new IllegalArgumentException("해당 영화에 대한 리뷰가 아닙니다.");
        }
        return new ReviewResponse(
                review.getId(),
                review.getContent(),
                review.getCreatedAt(),
                review.getModifiedAt()
        );
    }

    @Transactional
    public ReviewResponse updateReview(ReviewRequest request, Long movieId, Long reviewId){
        movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 영화입니다.")
        );

        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 리뷰입니다.")
        );
        if (!review.getMovie().getId().equals(movieId)) {
            throw new IllegalArgumentException("해당 영화에 대한 리뷰가 아닙니다.");
        }
        review.updateReview(request.getContent());
        return new ReviewResponse(
                review.getId(),
                review.getContent(),
                review.getCreatedAt(),
                review.getModifiedAt()
        );
    }

}
