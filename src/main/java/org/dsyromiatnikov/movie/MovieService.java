package org.dsyromiatnikov.movie;

import org.dsyromiatnikov.order.OrderRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class MovieService {
    private final OrderRepo orderRepo;
    private final MovieRepo repo;

    public MovieService(MovieRepo repo, OrderRepo orderRepo) {
        this.repo = repo;
        this.orderRepo = orderRepo;
    }

    public Optional<Movie> findById(Long id){
        return repo.findById(id);
    }

    public Page<Movie> findAll(Specification<Movie> spec, Pageable pageable){
        return repo.findAll(spec, pageable);
    }

    public void delete(Movie movie){
        repo.delete(movie);
    }

    public Movie save(Movie movie){
        return repo.save(movie);
    }


}
