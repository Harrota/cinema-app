package org.dsyromiatnikov.movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> findOne (@PathVariable Long id) {
        return new ResponseEntity<>(movieService.findById(id)
                .map(movieMapper::map)
                .orElse(null), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Movie movie = movieService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No movie with ID: " + id));

        movieService.delete(movie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
