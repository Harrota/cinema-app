package org.dsyromiatnikov.movie;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieDto map (Movie movie) {
        MovieDto dto = new MovieDto();
        BeanUtils.copyProperties(movie, dto);
        dto.setOrder(movie.getOrder().getId());
        return dto;
    }

    public Movie map (MovieDto movieDto) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieDto, movie, "id");
        return movie;
    }
}
