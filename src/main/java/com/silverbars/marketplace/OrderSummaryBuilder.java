package com.silverbars.marketplace;

import com.silverbars.domain.Order;
import com.silverbars.domain.OrderType;

import java.util.List;
import java.util.Map;

/**
 * Creates the order summary. Returns map key on order type with corresponding list of orders.
 */
public interface OrderSummaryBuilder {
    /**
     * Get the order summary for the given order type.
     * @return
     */
     Map<OrderType, List<Order>> getOrderSummary();
}
