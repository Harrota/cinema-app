package org.dsyromiatnikov.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dsyromiatnikov.movie.MovieDto;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String clientFullName;
    private String description;
    private String dateCreated;

    private List<MovieDto> movies = new ArrayList<>();
}
