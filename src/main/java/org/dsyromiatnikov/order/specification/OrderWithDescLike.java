package org.dsyromiatnikov.order.specification;

import lombok.AllArgsConstructor;
import org.dsyromiatnikov.order.Order;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class OrderWithDescLike implements Specification<Order> {

    private final String description;

    @Override
    public Predicate toPredicate(Root<Order> order, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (description == null){
            return cb.isTrue(cb.literal(true));
        }
        return cb.like(order.get("description"), "%" + description + "%");
    }
}
