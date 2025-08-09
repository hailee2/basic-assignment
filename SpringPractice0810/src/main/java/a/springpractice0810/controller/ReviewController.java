package a.springpractice0810.controller;

import a.springpractice0810.dto.ReviewRequest;
import a.springpractice0810.dto.ReviewResponse;
import a.springpractice0810.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/movies/{movieId}/reviews")
    public ResponseEntity<ReviewResponse> saveReview(
            @RequestBody ReviewRequest request,
            @PathVariable Long movieId
    ){
        return ResponseEntity.ok(reviewService.save(request,movieId));
    }

    @GetMapping("/movies/{movieId}/reviews")
    public ResponseEntity<List<ReviewResponse>> getAllReviews(
            @PathVariable Long movieId
    ){
        return ResponseEntity.ok(reviewService.findAll(movieId));
    }

    @GetMapping("/movies/{movieId}/reviews/{reviewId}")
    public ResponseEntity<ReviewResponse> getReview(
            @PathVariable Long movieId,
            @PathVariable Long reviewId
    ){
        return ResponseEntity.ok(reviewService.findReview(movieId,reviewId));
    }

    @PutMapping("/movies/{movieId}/reviews/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Long movieId,
            @RequestBody ReviewRequest request,
            @PathVariable Long reviewId
    ){
        return ResponseEntity.ok(reviewService.updateReview(request,movieId,reviewId));
    }
}
