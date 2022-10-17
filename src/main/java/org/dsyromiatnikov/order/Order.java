package org.dsyromiatnikov.order;

import lombok.Getter;
import lombok.Setter;
import org.dsyromiatnikov.base.AbstractEntity;
import org.dsyromiatnikov.movie.Movie;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    private String clientFullName;
    private String description;
    private LocalDate dateCreated;

    @ManyToMany(cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(name = "orders_movies",
            joinColumns = {
                    @JoinColumn(name = "movie_id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "order_id") })
    private List<Movie> movies = new ArrayList<>();
}
