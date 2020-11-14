package com.vv.vvspringbootreactivemongo.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class OrderService {

    @Autowired
    ReactiveMongoTemplate template;

    public Mono<Order> findById(String id) {
        return template.findById(id, Order.class);
    }

    public Flux<Order> findAll() {
        return template.findAll(Order.class);
    }

    public Mono<Order> save(Order order) {
        return template.save(order);
    }
}