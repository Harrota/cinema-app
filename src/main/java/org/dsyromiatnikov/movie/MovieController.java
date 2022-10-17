package org.dsyromiatnikov.movie;

import org.dsyromiatnikov.movie.exception.MovieException;
import org.dsyromiatnikov.movie.specification.MovieWithDescLike;
import org.dsyromiatnikov.movie.specification.MovieWithNameLike;
import org.dsyromiatnikov.movie.specification.MovieWithReleaseDateBetween;
import org.dsyromiatnikov.util.PaginationPayloadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/list")
    public ResponseEntity<?> list (@RequestParam(name = "name", required = false) String name,
                                   @RequestParam(name = "description", required = false) String description,
                                   @RequestParam(name = "releasedAfter", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate releasedAfter,
                                   @RequestParam(name = "releasedBefore", required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate releasedBefore,
                                   @PageableDefault(size = 5) Pageable page) {

        Specification<Movie> spec = Specification.where(new MovieWithNameLike(name))
                .and(new MovieWithDescLike(description))
                .and(new MovieWithReleaseDateBetween(releasedAfter, releasedBefore));

        return new ResponseEntity<>(getPaginationPayload(spec, page), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody MovieDto movieDto) {
        try {
            MovieDto responseDto = movieMapper.map(movieService.save(movieMapper.map(movieDto)));
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (MovieException ex){
            return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Movie movie = movieService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No movie with ID: " + id));

        movieService.delete(movie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public PaginationPayloadDto getPaginationPayload( Specification<Movie> spec, Pageable pageable) {
        Page<Movie> moviePage = movieService.findAll(spec, pageable);

        List<MovieDto> movies = moviePage.getContent().stream()
                .map(movieMapper::map)
                .collect(Collectors.toList());

        return new PaginationPayloadDto(pageable.getPageNumber(), pageable.getPageSize(), Math.toIntExact(moviePage.getTotalElements()), movies);
    }
}
