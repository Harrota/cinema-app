package org.dsyromiatnikov.order;

import lombok.Setter;
import org.dsyromiatnikov.movie.Movie;
import org.dsyromiatnikov.movie.MovieDto;
import org.dsyromiatnikov.movie.MovieMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Setter(onMethod_ = { @Autowired })
public class OrderMapper {

    private MovieMapper movieMapper;

    public OrderDto map (Order order) {
        OrderDto dto = new OrderDto();
        BeanUtils.copyProperties(order, dto, "movies");

        List<MovieDto> movies = order.getMovies()
                .stream().map(movieMapper::map).collect(Collectors.toList());
        dto.setMovies(movies);

        return dto;
    }

    public Order map (OrderDto dto) {
        Order order = new Order();
        BeanUtils.copyProperties(dto, order, "id", "movies");

        List<Movie> movies = dto.getMovies()
                .stream().map(movieMapper::map).collect(Collectors.toList());
        order.setMovies(movies);

        return order;
    }
}
