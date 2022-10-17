package org.dsyromiatnikov.order;

import org.dsyromiatnikov.movie.Movie;
import org.dsyromiatnikov.movie.MovieDto;
import org.dsyromiatnikov.movie.MovieMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

private final MovieMapper movieMapper;

    public OrderMapper(@Lazy MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    public OrderDto map (Order order) {
        OrderDto dto = new OrderDto();
        BeanUtils.copyProperties(order, dto, "movies");

        List<MovieDto> movies = order.getMovies()
                .stream().map(movieMapper::mapWithoutChild).collect(Collectors.toList());
        dto.setMovies(movies);

        return dto;
    }

    public Order map (OrderDto dto) {
        Order order = new Order();
        BeanUtils.copyProperties(dto, order, "movies");

        List<Movie> movies = dto.getMovies()
                .stream().map(movieMapper::map).collect(Collectors.toList());
        order.setMovies(movies);

        return order;
    }

    public OrderDto mapWithoutChild(Order order) {
        OrderDto dto = new OrderDto();
        BeanUtils.copyProperties(order, dto, "movies");
        return dto;
    }

}
