package org.dsyromiatnikov.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private String name;
    private String description;
    private String releaseDate;
    private Long order;
}
