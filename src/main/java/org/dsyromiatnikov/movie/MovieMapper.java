package org.dsyromiatnikov.movie;

import org.dsyromiatnikov.order.Order;
import org.dsyromiatnikov.order.OrderDto;
import org.dsyromiatnikov.order.OrderMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    private final OrderMapper orderMapper;

    public MovieMapper(@Lazy OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public MovieDto map (Movie movie) {
        MovieDto dto = new MovieDto();
        BeanUtils.copyProperties(movie, dto);

        if (movie.getOrders() != null){
            List<OrderDto> orders = movie.getOrders()
                    .stream().map(orderMapper::mapWithoutChild).collect(Collectors.toList());
            dto.setOrders(orders);
        }

        return dto;
    }

    public Movie map (MovieDto movieDto) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieDto, movie, "orders");

        if (movieDto.getOrders() != null){
            List<Order> orders = movieDto.getOrders()
                    .stream().map(orderMapper::map).collect(Collectors.toList());
            movie.setOrders(orders);
        }

        return movie;
    }

    public MovieDto mapWithoutChild (Movie movie) {
        MovieDto dto = new MovieDto();
        BeanUtils.copyProperties(movie, dto);
        return dto;
    }
}
