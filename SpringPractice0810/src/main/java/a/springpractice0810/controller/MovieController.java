package a.springpractice0810.controller;

import a.springpractice0810.dto.MovieRequest;
import a.springpractice0810.dto.MovieResponse;
import a.springpractice0810.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<MovieResponse> saveMovie(
            @RequestBody MovieRequest request
    ){
        return ResponseEntity.ok(movieService.save(request));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> getMovies(){
        return ResponseEntity.ok(movieService.findMovies());
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponse> findMovie(
            @PathVariable Long movieId
    ){
        return ResponseEntity.ok(movieService.findMovie(movieId));
    }

    @PutMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponse> updateMovie(
        @RequestBody MovieRequest request,
        @PathVariable Long movieId
    ){
      return ResponseEntity.ok(movieService.updateMovie(movieId, request));
    }
}
