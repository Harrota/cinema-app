package org.dsyromiatnikov.order;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepo repo;

    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    public Optional<Order> findById(Long id){
        return repo.findById(id);
    }

    public void delete(Order order){
        repo.delete(order);
    }
}
