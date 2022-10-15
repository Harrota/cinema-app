package org.dsyromiatnikov.order;

import lombok.Getter;
import lombok.Setter;
import org.dsyromiatnikov.base.AbstractEntity;
import org.dsyromiatnikov.movie.Movie;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    private String clientFullName;
    private String description;
    private String dateCreated;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movie> movies = new ArrayList<>();
}
