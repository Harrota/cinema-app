package org.dsyromiatnikov.order;

import lombok.*;
import org.dsyromiatnikov.movie.MovieDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderDto {
    private Long id;
    private String clientFullName;
    private String description;
    private LocalDate dateCreated;

    private List<MovieDto> movies = new ArrayList<>();
}
