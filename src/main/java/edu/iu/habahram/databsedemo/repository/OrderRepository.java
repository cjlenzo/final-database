package edu.iu.habahram.databsedemo.repository;

import edu.iu.habahram.databsedemo.model.Customer;
import edu.iu.habahram.databsedemo.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository
        extends CrudRepository<Order, Integer> {
    List<Order> findAllByCustomerUserName(String customerUserName);
}
