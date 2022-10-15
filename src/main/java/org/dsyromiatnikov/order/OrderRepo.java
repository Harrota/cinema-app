package org.dsyromiatnikov.order;

import org.dsyromiatnikov.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Movie, Long> {
}
