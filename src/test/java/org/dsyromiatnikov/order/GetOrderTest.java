package org.dsyromiatnikov.order;

import org.dsyromiatnikov.movie.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GetOrderTest {

    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        Movie movie = new Movie();
        movie.setName("name");
        movie.setDescription("desc");
        movie.setReleaseDate(LocalDate.of(2022, 1, 1));

        Movie movie1 = new Movie();
        movie.setName("name1");
        movie.setDescription("desc1");
        movie.setReleaseDate(LocalDate.of(2022, 1, 2));

        Order order = new Order();
        order.setId(1L);
        order.setClientFullName("clientName");
        order.setDescription("desc");
        order.setDateCreated(LocalDate.of(2022, 2, 1));
        order.setMovies(Arrays.asList(movie, movie1));

        when(orderService.findById(1L)).thenReturn(Optional.of(order));
    }

    @Test
    public void getOrderTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/order/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"clientFullName\":\"clientName\",\"description\":\"desc\",\"dateCreated\":\"2022-02-01\",\"movies\":[{\"id\":null,\"name\":\"name1\",\"description\":\"desc1\",\"releaseDate\":\"2022-01-02\",\"orders\":null},{\"id\":null,\"name\":null,\"description\":null,\"releaseDate\":null,\"orders\":null}]}"));
    }
}
