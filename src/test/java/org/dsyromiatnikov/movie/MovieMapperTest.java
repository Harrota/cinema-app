package org.dsyromiatnikov.movie;

import org.dsyromiatnikov.order.Order;
import org.dsyromiatnikov.order.OrderDto;
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
public class MovieMapperTest {

    @Autowired
    private MovieMapper mapper;

    @Test
    public void mapToDtoTest() {
        Order order = new Order();
        order.setClientFullName("clientName");
        order.setDescription("desc");
        order.setDateCreated(LocalDate.of(2022, 1, 1));

        Order order1 = new Order();
        order.setClientFullName("clientName1");
        order.setDescription("desc1");
        order.setDateCreated(LocalDate.of(2022, 1, 2));

        Movie movie = new Movie();
        movie.setName("name");
        movie.setDescription("desc");
        movie.setReleaseDate(LocalDate.of(2022, 2, 1));
        movie.setOrders(Arrays.asList(order, order1));
        MovieDto mappedDto = mapper.map(movie);

        assertEquals(movie.getName(), mappedDto.getName());
        assertEquals(movie.getDescription(), mappedDto.getDescription());
        assertEquals(movie.getReleaseDate(), mappedDto.getReleaseDate());
        assertEqualsOrderWithDto(order, mappedDto.getOrders().get(0));
        assertEqualsOrderWithDto(order1, mappedDto.getOrders().get(1));
    }

    private static void assertEqualsOrderWithDto(Order order, OrderDto orderDto){
        assertEquals(orderDto.getClientFullName(), order.getClientFullName());
        assertEquals(orderDto.getDescription(), order.getDescription());
        assertEquals(orderDto.getDateCreated(), order.getDateCreated());
    }
}
