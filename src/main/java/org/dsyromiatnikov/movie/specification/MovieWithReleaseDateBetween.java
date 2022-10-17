package org.dsyromiatnikov.movie.specification;

import lombok.AllArgsConstructor;
import org.dsyromiatnikov.movie.Movie;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

@AllArgsConstructor
public class MovieWithReleaseDateBetween implements Specification<Movie> {

    private final LocalDate releasedAfter;
    private final LocalDate releasedBefore;

    @Override
    public Predicate toPredicate(Root<Movie> movie, CriteriaQuery<?> query, CriteriaBuilder cb) {

        if(releasedBefore != null){
            cb.and(cb.lessThanOrEqualTo(movie.get("releaseDate"), this.releasedBefore));
        }

        if(releasedAfter != null){
            cb.and(cb.greaterThanOrEqualTo(movie.get("releaseDate"), this.releasedAfter));
        }

        return cb.isTrue(cb.literal(true));
    }
}
