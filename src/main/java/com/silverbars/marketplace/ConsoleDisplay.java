package com.silverbars.marketplace;

import com.silverbars.domain.Order;
import com.silverbars.domain.OrderType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Displays the order board orders onto the console.
 */
public class ConsoleDisplay implements Display{
    @Override
    public void displayOrders(Map<OrderType, List<Order>> summary) {
        StringBuilder sb = new StringBuilder("[Live Order Board]\n\n");
        sb.append("BUY ORDERS:\n");
        sb.append(getOrdersDisplay(summary.get(OrderType.BUY))).append("\n\n");
        sb.append("SELL ORDERS:\n");
        sb.append(getOrdersDisplay(summary.get(OrderType.SELL))).append("\n\n");
        System.out.println(sb);
    }
    private String getOrdersDisplay(List<Order> orders){
        return orders.stream().map(o -> o.toString()).collect(Collectors.joining("\n"));
    }
}
