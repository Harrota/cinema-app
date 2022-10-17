package org.dsyromiatnikov.movie;

import lombok.*;
import org.dsyromiatnikov.order.OrderDto;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MovieDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private List<OrderDto> orders;
}
