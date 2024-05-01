package edu.iu.habahram.databsedemo.services;

import edu.iu.habahram.databsedemo.model.Order;
import edu.iu.habahram.databsedemo.repository.OrderRepository;
import org.aspectj.lang.annotation.After;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class OrderServiceTest {

    OrderService orderService;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );


    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }


    static void setUp() {
        postgres.start();
    }


    static void tearDown() {
        postgres.stop();
    }


    void add() {
        Order order = new Order();
        order.setFlowerId(2);
        order.setRecipientName("Jane");
        order.setTotalCost(50.56f);
        order.setCustomerUserName("John");
        orderService.add(order);
    }


    void findAllByCustomer() {
        List<Order> orders = orderService.findAllByCustomer("John");
        Assert.assertEquals(orders.size(), 1);
        Assert.assertEquals("Jane", orders.get(0).getRecipientName());
        Assert.assertEquals(2, orders.get(0).getFlowerId().intValue());
    }


    void searchByFlowerId() {
        Order order = new Order();
        order.setFlowerId(2);
        List<Order> result = orderService.search(order);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(2, result.get(0).getFlowerId().intValue());
    }
}