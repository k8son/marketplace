package com.silverbars.marketplace;

import com.silverbars.domain.Order;
import com.silverbars.domain.OrderType;

import java.util.List;
import java.util.Map;

/**
 * Physically displays the order board.
 */
public interface Display {
    /**
     * Displays the list of orders.
     * @param summary
     */
    void displayOrders(Map<OrderType, List<Order>> summary);
}
