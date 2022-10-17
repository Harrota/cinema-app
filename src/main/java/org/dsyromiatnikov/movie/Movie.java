package org.dsyromiatnikov.movie;

import lombok.Getter;
import lombok.Setter;
import org.dsyromiatnikov.base.AbstractEntity;
import org.dsyromiatnikov.order.Order;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie extends AbstractEntity {

    private String name;
    private String description;
    private LocalDate releaseDate;

    @ManyToMany(mappedBy = "movies")
    private List<Order> orders;
}
