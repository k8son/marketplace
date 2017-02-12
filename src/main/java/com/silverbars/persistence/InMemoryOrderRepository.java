package com.silverbars.persistence;

import com.silverbars.domain.Order;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In memory implementation of a Order Repository. Uses a ConcurrentHashMap to allow multithreaded access to the order store.
 */
public class InMemoryOrderRepository implements OrderRepository {
    private final ConcurrentHashMap<UUID, Order> orders = new ConcurrentHashMap<UUID, Order>();

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public void removeOrder(UUID orderId) {
        orders.remove(orderId);
    }

    public Order getOrder(UUID orderId) {
        return orders.get(orderId);
    }

    public Collection<Order> getAllOrders() {
        return orders.values();
    }
}
