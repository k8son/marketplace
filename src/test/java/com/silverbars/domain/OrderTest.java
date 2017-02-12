package com.silverbars.domain;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Specific tests for the sort ordering of orders.
 */
public class OrderTest {
    private Order.OrderBuilder orderBuilder;

    @Before
    public void setup(){
        orderBuilder = new Order.OrderBuilder();
    }

    @Test
    public void shouldListSELLOrdersInAscendingOrder(){
        List<Order> orders = createOrders(OrderType.SELL);
        Collections.sort(orders);
        assertThat(orders.get(0).getUnitPrice(), is(0.8d));
        assertThat(orders.get(1).getUnitPrice(), is(1.0d));
        assertThat(orders.get(2).getUnitPrice(), is(3.3d));
        assertThat(orders.get(3).getUnitPrice(), is(4.2d));
        assertThat(orders.get(4).getUnitPrice(), is(6.6d));
    }

    @Test
    public void shouldListBUYOrdersInDescendingOrder(){
        List<Order> orders = createOrders(OrderType.BUY);
        Collections.sort(orders);
        assertThat(orders.get(0).getUnitPrice(), is(6.6d));
        assertThat(orders.get(1).getUnitPrice(), is(4.2d));
        assertThat(orders.get(2).getUnitPrice(), is(3.3d));
        assertThat(orders.get(3).getUnitPrice(), is(1.0d));
        assertThat(orders.get(4).getUnitPrice(), is(0.8d));
    }

    @Test
    public void shouldGroupBUYAndSELLOrdersInRespectiveOrder(){
        List<Order> orders = new ArrayList<>(createOrders(OrderType.SELL));
        orders.addAll(createOrders(OrderType.BUY));
        Collections.sort(orders);

        assertThat(orders.get(0).getUnitPrice(), is(6.6d));
        assertThat(orders.get(0).getOrderType().name(), is("BUY"));
        assertThat(orders.get(1).getUnitPrice(), is(4.2d));
        assertThat(orders.get(1).getOrderType().name(), is("BUY"));
        assertThat(orders.get(2).getUnitPrice(), is(3.3d));
        assertThat(orders.get(2).getOrderType().name(), is("BUY"));
        assertThat(orders.get(3).getUnitPrice(), is(1.0d));
        assertThat(orders.get(3).getOrderType().name(), is("BUY"));
        assertThat(orders.get(4).getUnitPrice(), is(0.8d));
        assertThat(orders.get(4).getOrderType().name(), is("BUY"));

        assertThat(orders.get(5).getUnitPrice(), is(0.8d));
        assertThat(orders.get(5).getOrderType().name(), is("SELL"));
        assertThat(orders.get(6).getUnitPrice(), is(1.0d));
        assertThat(orders.get(6).getOrderType().name(), is("SELL"));
        assertThat(orders.get(7).getUnitPrice(), is(3.3d));
        assertThat(orders.get(7).getOrderType().name(), is("SELL"));
        assertThat(orders.get(8).getUnitPrice(), is(4.2d));
        assertThat(orders.get(8).getOrderType().name(), is("SELL"));
        assertThat(orders.get(9).getUnitPrice(), is(6.6d));
        assertThat(orders.get(9).getOrderType().name(), is("SELL"));
    }

    private List<Order> createOrders(OrderType orderType){
        return Arrays.asList(
                orderBuilder.userid("km").orderType(orderType).quantity(5d).unitPrice(3.3d).build(),
                orderBuilder.userid("km").orderType(orderType).quantity(5d).unitPrice(4.2d).build(),
                orderBuilder.userid("km").orderType(orderType).quantity(5d).unitPrice(1.0d).build(),
                orderBuilder.userid("km").orderType(orderType).quantity(5d).unitPrice(6.6d).build(),
                orderBuilder.userid("km").orderType(orderType).quantity(5d).unitPrice(0.8d).build());
    }
}