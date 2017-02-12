package com.silverbars.persistence;

import com.silverbars.domain.Order;
import com.silverbars.domain.OrderType;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class InMemoryOrderRepositoryTest {
    private OrderRepository orderRepository;

    @Before
    public void setUp(){
        orderRepository = new InMemoryOrderRepository();
    }

    @Test
    public void shouldAddOrder(){
        UUID id = UUID.randomUUID();
        Order order = new Order(id, "kmiah", OrderType.BUY, 1.0, 1.0);
        orderRepository.addOrder(order);
        Collection<Order> orders = orderRepository.getAllOrders();
        assertThat(orders.size(), is(1));
        assertThat(containsOrder(id, orders), is(true));
    }

    @Test
    public void shouldAddMultipleOrders(){
        UUID id1 = UUID.randomUUID();
        Order order1 = new Order(id1, "kmiah", OrderType.BUY, 1.0, 1.0);
        orderRepository.addOrder(order1);

        UUID id2 = UUID.randomUUID();
        Order order2 = new Order(id2, "kmiah", OrderType.SELL, 1.0, 1.0);
        orderRepository.addOrder(order2);

        Collection<Order> orders = orderRepository.getAllOrders();
        assertThat(orders.size(), is(2));
        assertThat(containsOrder(id1, orders), is(true));
        assertThat(containsOrder(id2, orders), is(true));

    }

    @Test
    public void shouldRemoveOrder(){
        UUID id = UUID.randomUUID();
        Order order = new Order(id, "kmiah", OrderType.BUY, 1.0, 1.0);
        orderRepository.addOrder(order);
        Collection<Order> orders = orderRepository.getAllOrders();
        assertThat(orders.size(), is(1));

        orderRepository.removeOrder(id);
        orders = orderRepository.getAllOrders();
        assertThat(orders.size(), is(0));

    }

    @Test
    public void shouldNotDeleteAnyOrdersIfOrderIdDoesNotMatch(){
        UUID id = UUID.randomUUID();
        Order order = new Order(id, "kmiah", OrderType.BUY, 1.0, 1.0);
        orderRepository.addOrder(order);
        Collection<Order> orders = orderRepository.getAllOrders();
        assertThat(orders.size(), is(1));

        orderRepository.removeOrder(UUID.randomUUID());
        orders = orderRepository.getAllOrders();
        assertThat(orders.size(), is(1));

    }

    @Test
    @Ignore("This has already been tested as part of the assertions in the above tests")
    public void shouldReturnAllOrders(){

    }

    private boolean containsOrder(UUID id, Collection<Order> orders){
        return orders.stream().filter(o -> o.getId().equals(id)).findAny().isPresent();
    }

}