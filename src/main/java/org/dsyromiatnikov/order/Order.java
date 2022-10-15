package org.dsyromiatnikov.order;

import lombok.Getter;
import lombok.Setter;
import org.dsyromiatnikov.base.AbstractEntity;
import org.dsyromiatnikov.movie.Movie;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movie> movies = new ArrayList<>();
}
