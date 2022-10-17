package org.dsyromiatnikov.order;

import org.dsyromiatnikov.exception.ExceptionWrapper;
import org.dsyromiatnikov.movie.exception.MovieException;
import org.dsyromiatnikov.order.exception.OrderException;
import org.dsyromiatnikov.order.specification.OrderWithDateCreatedBetween;
import org.dsyromiatnikov.order.specification.OrderWithDescLike;
import org.dsyromiatnikov.order.specification.OrderWithFullNameLike;
import org.dsyromiatnikov.util.PaginationPayloadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/list")
    public ResponseEntity<?> list (@RequestParam(name = "clientFullName", required = false) String clientFullName,
                                   @RequestParam(name = "description", required = false) String description,
                                   @RequestParam(name = "createdAfter", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAfter,
                                   @RequestParam(name = "createdBefore", required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate createdBefore,
                                   @PageableDefault(size = 5) Pageable page) {

        Specification<Order> spec = Specification.where(new OrderWithFullNameLike(clientFullName))
                .and(new OrderWithDescLike(description))
                .and(new OrderWithDateCreatedBetween(createdAfter, createdBefore));

        return new ResponseEntity<>(getPaginationPayload(spec, page), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderDto orderDto) {
        try {
            OrderDto responseDto = orderMapper.map(orderService.save(orderMapper.map(orderDto)));
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (OrderException ex){
            return new ResponseEntity<>(new ExceptionWrapper(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Order order = orderService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No order with ID: " + id));

        orderService.delete(order);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public PaginationPayloadDto getPaginationPayload(Specification<Order> spec, Pageable pageable) {
        Page<Order> orderPage = orderService.findAll(spec, pageable);

        List<OrderDto> orders = orderPage.getContent().stream()
                .map(orderMapper::map)
                .collect(Collectors.toList());

        return new PaginationPayloadDto(pageable.getPageNumber(), pageable.getPageSize(), Math.toIntExact(orderPage.getTotalElements()), orders);
    }

    @ExceptionHandler({ MovieException.class })
    public ResponseEntity<?> handleException(MovieException ex) {
        return new ResponseEntity<>(new ExceptionWrapper(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
