package org.dsyromiatnikov.movie.specification;

import lombok.AllArgsConstructor;
import org.dsyromiatnikov.movie.Movie;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class MovieWithDescLike implements Specification<Movie> {

    private final String description;

    @Override
    public Predicate toPredicate(Root<Movie> movie, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (description == null){
            return cb.isTrue(cb.literal(true));
        }
        return cb.like(movie.get("description"), "%" + description + "%");
    }
}
