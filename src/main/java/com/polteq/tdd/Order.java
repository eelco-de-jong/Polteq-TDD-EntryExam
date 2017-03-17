package com.polteq.tdd;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by jongd on 9-3-2017.
 */
public class Order {

    final String orderId;
    public String clientName;
    public OrderStatus orderStatus;
    public ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();

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
        ArrayList<String> productDetails = new ArrayList<String>();
        productDetails.add(product.getProductName());
        productDetails.add(product.getProductPPU());
        productDetails.add(product.getProductType());
        productDetails.add(String.valueOf(product.getProductAmount()));
        orderList.add(productDetails);
    }

    public ArrayList<String> getProductByName(String name) {
        //Loop through list of lists
        for (ArrayList<String> list : orderList) {
            if (list.get(0) == name) {
                return list;
            }
        }
        return null;
    }

    public String calculateOrderAmountForClientName(String name) {
        for (ArrayList<String> list : orderList) {
            for (String l : list) {
                //TODO: Add code to calculate price per product and sum those amounts
            }
        }
    }
}
