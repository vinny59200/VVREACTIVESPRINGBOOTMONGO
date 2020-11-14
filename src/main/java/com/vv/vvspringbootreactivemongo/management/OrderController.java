package com.vv.vvspringbootreactivemongo.management;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("adminDept")
@AllArgsConstructor
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public Flux<Order> getAll() {
        System.out.println("::will returns ALL Orders records::");
        return orderService.findAll();
    }

    @GetMapping("{_id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public Mono<Order> getById(@PathVariable("_id") final String id) {
        System.out.println("::will return a Order record::");
        return orderService.findById(id);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public Mono save(@RequestBody final Order order) {
        System.out.println("will insert the order's record :: " + order.get_id() + " :: " + order.isProcess());
        return orderService.save(order);
    }

}
