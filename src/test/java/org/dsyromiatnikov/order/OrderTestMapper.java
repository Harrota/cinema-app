package org.dsyromiatnikov.order;

import org.dsyromiatnikov.movie.Movie;
import org.dsyromiatnikov.movie.MovieDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTestMapper {
    @Autowired
    private OrderMapper mapper;

    @Test
    public void mapToDtoTest() {
        Movie movie = new Movie();
        movie.setName("name");
        movie.setDescription("desc");
        movie.setReleaseDate(LocalDate.of(2022, 1, 1));

        Movie movie1 = new Movie();
        movie.setName("name1");
        movie.setDescription("desc1");
        movie.setReleaseDate(LocalDate.of(2022, 1, 2));

        Order order = new Order();
        order.setClientFullName("clientName");
        order.setDescription("desc");
        order.setDateCreated(LocalDate.of(2022, 2, 1));
        order.setMovies(Arrays.asList(movie, movie1));
        OrderDto mappedDto = mapper.map(order);

        assertEquals(order.getClientFullName(), mappedDto.getClientFullName());
        assertEquals(order.getDescription(), mappedDto.getDescription());
        assertEquals(order.getDateCreated(), mappedDto.getDateCreated());
        assertEqualsMovieWithDto(movie, mappedDto.getMovies().get(0));
        assertEqualsMovieWithDto(movie1, mappedDto.getMovies().get(1));
    }

    private static void assertEqualsMovieWithDto(Movie movie, MovieDto movieDto){
        assertEquals(movieDto.getName(), movie.getName());
        assertEquals(movieDto.getDescription(), movie.getDescription());
        assertEquals(movieDto.getReleaseDate(), movie.getReleaseDate());
    }
}
