package com.vv.vvspringbootreactivemongo;

import com.vv.vvspringbootreactivemongo.management.Order;
import com.vv.vvspringbootreactivemongo.management.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
class VvspringbootreactivemongoApplicationTests {
    @Autowired
    private OrderService service;

    @Test
    void contextLoads() {
    }

    @Test
    public void saveThenUpdate() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        service.save(Order.builder()._id(randomUUIDString).process(false).build()).block();
        Mono<Order> orderResult = service.findById(randomUUIDString);

        StepVerifier
                .create(orderResult)
                .assertNext(order -> {
                    assertEquals(randomUUIDString, order.get_id());
                    assertEquals(false, order.isProcess());
                })
                .expectComplete()
                .verify();

        service.save(Order.builder()._id(randomUUIDString).process(true).build()).block();
        Mono<Order> updatedOrderResult = service.findById(randomUUIDString);

        StepVerifier
                .create(updatedOrderResult)
                .assertNext(order -> {
                    assertEquals(randomUUIDString, order.get_id());
                    assertEquals(true, order.isProcess());
                })
                .expectComplete()
                .verify();
    }


}
