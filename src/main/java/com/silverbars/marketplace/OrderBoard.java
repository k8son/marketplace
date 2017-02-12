package com.silverbars.marketplace;

import com.silverbars.domain.Order;
import com.silverbars.domain.OrderType;
import com.silverbars.persistence.OrderRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Representation of a simplistic Order board. Allows the registration and cancellation of orders, as well
 * as providing a summary of all current orders.
 */
public class OrderBoard {
    private final OrderRepository orderRepository;
    private final OrderValidationHelper orderValidatorHelper;
    private final OrderSummaryBuilder orderSummaryBuilder;
    private final Display display;

    public OrderBoard(OrderRepository orderRepository, OrderValidationHelper orderValidatorHelper,
                      OrderSummaryBuilder orderSummaryBuilder, Display display){
        this.orderRepository = orderRepository;
        this.orderValidatorHelper = orderValidatorHelper;
        this.orderSummaryBuilder = orderSummaryBuilder;
        this.display = display;
    }

    /**
     * Registers an order.
     * @param order
     */
    public void registerOrder(Order order){
        orderValidatorHelper.validate(order);
        orderRepository.addOrder(order);
    }

    /**
     * Cancels an order.
     * @param orderId
     */
    public void cancelOrder(UUID orderId){
        orderRepository.removeOrder(orderId);
    }

    /**
     * Displays the order board.
     * @return
     */
    public void displayOrderBoard(){
        Map<OrderType, List<Order>> summary = orderSummaryBuilder.getOrderSummary();
        display.displayOrders(summary);
    }
}
