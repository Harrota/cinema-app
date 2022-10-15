package org.dsyromiatnikov.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findOne (@PathVariable Long id) {
        return new ResponseEntity<>(orderService.findById(id)
                .map(orderMapper::map)
                .orElse(null), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Order order = orderService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No order with ID: " + id));

        orderService.delete(order);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
