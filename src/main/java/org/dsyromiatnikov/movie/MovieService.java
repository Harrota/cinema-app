package org.dsyromiatnikov.movie;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepo repo;

    public MovieService(MovieRepo repo) {
        this.repo = repo;
    }

    public Optional<Movie> findById(Long id){
        return repo.findById(id);
    }

    public void delete(Movie movie){
        repo.delete(movie);
    }
}
