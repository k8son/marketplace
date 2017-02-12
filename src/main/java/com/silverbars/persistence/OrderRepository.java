package com.silverbars.persistence;

import com.silverbars.domain.Order;

import java.util.Collection;
import java.util.UUID;

/**
 * Repository for Orders.
 */
public interface OrderRepository {
    /**
     * Adds an order.
     * @param order
     */
    void addOrder(Order order);

    /**
     * Removes an order.
     * @param orderId
     */
    void removeOrder(UUID orderId);

    /**
     * Retrieves an order.
     * @param order
     * @return
     */
    Order getOrder(UUID order);

    /**
     * Returns all orders.
     * @return
     */
    Collection<Order> getAllOrders();
}
