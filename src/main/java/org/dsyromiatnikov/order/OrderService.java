package org.dsyromiatnikov.order;

import org.dsyromiatnikov.movie.Movie;
import org.dsyromiatnikov.movie.MovieRepo;
import org.dsyromiatnikov.movie.exception.MovieException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    private final MovieRepo movieRepo;
    private final OrderRepo repo;

    public OrderService(OrderRepo repo, MovieRepo movieRepo) {
        this.repo = repo;
        this.movieRepo = movieRepo;
    }

    public Optional<Order> findById(Long id){
        return repo.findById(id);
    }

    public Page<Order> findAll(Specification<Order> spec, Pageable pageable){
        return repo.findAll(spec, pageable);
    }

    public void delete(Order order){
        repo.delete(order);
    }

    public Order save(Order order) {
        ArrayList<Movie> orderMovies = new ArrayList<>();
        for (Movie movie : order.getMovies()){
            if (movie.getId() == null){
                throw new MovieException("Movie should be exist");
            }

            Optional<Movie> movieOptional = movieRepo.findById(movie.getId());
            if (movieOptional.isPresent()){
                orderMovies.add(movieOptional.get());
            } else {
                throw new MovieException("Movie doesn't exist");
            }
        }
        order.setMovies(orderMovies);
        return repo.save(order);
    }
}
