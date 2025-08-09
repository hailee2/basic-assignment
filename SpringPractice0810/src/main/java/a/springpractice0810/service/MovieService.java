package a.springpractice0810.service;

import a.springpractice0810.dto.MovieRequest;
import a.springpractice0810.dto.MovieResponse;
import a.springpractice0810.entity.Movie;
import a.springpractice0810.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public MovieResponse save(MovieRequest request) {
        Movie movie = new Movie(request.getTitle());
        Movie savedMovie = movieRepository.save(movie);
        return new MovieResponse(
                savedMovie.getId(),
                savedMovie.getTitle(),
                savedMovie.getCreatedAt(),
                savedMovie.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<MovieResponse> findMovies(){
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movie -> new MovieResponse(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getCreatedAt(),
                        movie.getModifiedAt()
                )).toList();
    }

    @Transactional(readOnly = true)
    public MovieResponse findMovie(Long movieId){
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 영화입니다.")
        );
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getCreatedAt(),
                movie.getModifiedAt()
        );
    }

    @Transactional
    public MovieResponse updateMovie(Long movieId, MovieRequest request){
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 영화입니다.")
        );
        movie.updateMovie(request.getTitle());
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getCreatedAt(),
                movie.getModifiedAt()
        );
    }


}
