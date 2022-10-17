package org.dsyromiatnikov.movie;

import org.dsyromiatnikov.order.Order;
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
public class GetMovieTest {

    @MockBean
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        Order order = new Order();
        order.setClientFullName("clientName");
        order.setDescription("desc");
        order.setDateCreated(LocalDate.of(2022, 1, 1));

        Order order1 = new Order();
        order.setClientFullName("clientName1");
        order.setDescription("desc1");
        order.setDateCreated(LocalDate.of(2022, 1, 2));

        Movie movie = new Movie();
        movie.setId(1L);
        movie.setName("name");
        movie.setDescription("desc");
        movie.setReleaseDate(LocalDate.of(2022, 2, 1));
        movie.setOrders(Arrays.asList(order, order1));

        when(movieService.findById(1L)).thenReturn(Optional.of(movie));
    }

    @Test
    public void getOrderTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"name\",\"description\":\"desc\",\"releaseDate\":\"2022-02-01\",\"orders\":[{\"id\":null,\"clientFullName\":\"clientName1\",\"description\":\"desc1\",\"dateCreated\":\"2022-01-02\",\"movies\":[]},{\"id\":null,\"clientFullName\":null,\"description\":null,\"dateCreated\":null,\"movies\":[]}]}"));
    }
}
