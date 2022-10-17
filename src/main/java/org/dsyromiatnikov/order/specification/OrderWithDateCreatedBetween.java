package org.dsyromiatnikov.order.specification;

import lombok.AllArgsConstructor;
import org.dsyromiatnikov.order.Order;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

@AllArgsConstructor
public class OrderWithDateCreatedBetween implements Specification<Order> {

    private final LocalDate createdAfter;
    private final LocalDate createdBefore;

    @Override
    public Predicate toPredicate(Root<Order> order, CriteriaQuery<?> query, CriteriaBuilder cb) {

        if(createdBefore != null){
            cb.and(cb.lessThanOrEqualTo(order.get("dateCreated"), this.createdBefore));
        }

        if(createdAfter != null){
            cb.and(cb.greaterThanOrEqualTo(order.get("dateCreated"), this.createdAfter));
        }

        return cb.isTrue(cb.literal(true));
    }
}
