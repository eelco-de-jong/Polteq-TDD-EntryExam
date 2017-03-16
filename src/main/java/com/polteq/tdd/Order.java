package com.polteq.tdd;

import java.util.UUID;

/**
 * Created by jongd on 9-3-2017.
 */
public class Order {

    final String orderId;
    public String clientName;
    public OrderStatus orderStatus;

    Order() {
        orderId = setOrderId();
        clientName = "";
        orderStatus = OrderStatus.NEW;
    }

    public String setOrderId() {
        String orderId = UUID.randomUUID().toString();
        return orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setClientName(String name) {
        if (orderStatus != OrderStatus.COMPLETED) {
            clientName = name;
        }
    }

    public String getClientName() {
        return clientName;
    }

    public void setOrderStatus(OrderStatus status) {
        orderStatus = status;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void addProduct(Product product) {

    }
}
