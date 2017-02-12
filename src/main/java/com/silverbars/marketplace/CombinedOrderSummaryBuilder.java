package com.silverbars.marketplace;

import com.silverbars.domain.Order;
import com.silverbars.domain.OrderType;
import com.silverbars.persistence.OrderRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Builds an order summary by combining orders where they are matching
 * unit price.
 */
public class CombinedOrderSummaryBuilder implements OrderSummaryBuilder{
    private final OrderRepository orderRespository;
    private final Order.OrderBuilder orderBuilder;

    public CombinedOrderSummaryBuilder(OrderRepository orderRespository){
        this.orderRespository = orderRespository;
        orderBuilder = new Order.OrderBuilder();
    }

    @Override
    public Map<OrderType, List<Order>> getOrderSummary() {
        Collection<Order> orders = orderRespository.getAllOrders();
        Map<OrderType, List<Order>> summary = new HashMap<>();
        summary.put(OrderType.BUY, getSummaryForOrderType(orders, OrderType.BUY));
        summary.put(OrderType.SELL, getSummaryForOrderType(orders, OrderType.SELL));
        return summary;
    }

    private List<Order> getSummaryForOrderType(Collection<Order> orders, OrderType orderType){
        List<Order> summarisedOrders = getFilteredOrders(orders, orderType);
        summarisedOrders = getCombinedOrders(summarisedOrders);
        Collections.sort(summarisedOrders);
        return summarisedOrders;
    }

    private List<Order> getFilteredOrders(Collection<Order> orders, OrderType orderType){
        // Return all (sorted) orders for the given type as a list
        return orders.stream().filter(o -> o.getOrderType().equals(orderType)).collect(Collectors.toList());
    }

    private List<Order> getCombinedOrders(List<Order> orders) {
        // Use a temporary map (keyed) on the unit price to combine all orders for the same unit price
        Map<Double, Order> combined = new HashMap<>();
        orders.stream().forEach(o -> {
            if (combined.containsKey(o.getUnitPrice())){
                // Combine this order with the existing
                Order existing = combined.get(o.getUnitPrice());
                existing.combineWith(o);
            }
            else {
                combined.put(o.getUnitPrice(), o);
            }
        });

        return new ArrayList<Order>(combined.values());
    }



}
