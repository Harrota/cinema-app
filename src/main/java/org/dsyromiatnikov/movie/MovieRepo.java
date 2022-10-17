package org.dsyromiatnikov.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieRepo extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
}
