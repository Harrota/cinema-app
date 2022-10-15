package org.dsyromiatnikov.movie;

import lombok.Getter;
import lombok.Setter;
import org.dsyromiatnikov.base.AbstractEntity;
import org.dsyromiatnikov.order.Order;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie extends AbstractEntity {

    private String name;
    private String description;
    private String releaseDate;

    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "FK_MOVIES_ORDER_ID"))
    private Order order;
}
