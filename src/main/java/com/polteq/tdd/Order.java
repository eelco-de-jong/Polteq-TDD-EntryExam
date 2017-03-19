package com.polteq.tdd;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Eelco de Jong on 9-3-2017.
 */
class Order {

    private final String orderId = setOrderId();
    private String clientName = "";
    private OrderStatus orderStatus;
    private ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();

    Order() {
        orderStatus = OrderStatus.NEW;
    }

    private String setOrderId() {
        return UUID.randomUUID().toString();
    }

    String getOrderId() {
        return orderId;
    }

    void setClientName(String name) {
        if (orderStatus != OrderStatus.COMPLETED) {
            clientName = name;
        }
    }

    String getClientName() {
        return clientName;
    }

    void setOrderStatus(OrderStatus status) {
        orderStatus = status;
    }

    OrderStatus getOrderStatus() {
        return orderStatus;
    }

    void addProduct(LiquidProduct product) {
        ArrayList<String> productDetails = new ArrayList<String>();
        productDetails.add(product.getProductName()); //0
        productDetails.add(product.getProductPPU()); //1
        productDetails.add(product.getProductType()); //2
        productDetails.add(String.valueOf(product.getProductAmount())); //3
        productDetails.add(product.getCalculationFactor()); //4
        orderList.add(productDetails);
    }

    void addProduct(SolidProduct product) {
        ArrayList<String> productDetails = new ArrayList<String>();
        productDetails.add(product.getProductName()); //0
        productDetails.add(product.getProductPPU()); //1
        productDetails.add(product.getProductType()); //2
        productDetails.add(String.valueOf(product.getProductAmount())); //3
        productDetails.add(product.getCalculationFactor()); //4
        orderList.add(productDetails);
    }

    ArrayList<String> getProductByName(String name) {
        //Loop through list of lists
        for (ArrayList<String> list : orderList) {
            if (list.get(0).equals(name)) {
                return list;
            }
        }
        return null;
    }

    BigDecimal calculateOrderAmountForClientName(String name) {
        BigDecimal orderAmount = new BigDecimal("0");
        for (ArrayList<String> list : orderList) {
            BigDecimal bdPPU = new BigDecimal(list.get(1));
            BigDecimal bdProductAmount = new BigDecimal(list.get(3));
            BigDecimal bdDivisionValue = new BigDecimal(list.get(4));
            BigDecimal productCalculatedAmount = calculateProductAmount(bdPPU, bdProductAmount, bdDivisionValue);
            orderAmount = orderAmount.add(productCalculatedAmount);
            orderAmount = orderAmount.setScale(2, RoundingMode.CEILING);
        }
        return orderAmount;
    }

    private BigDecimal calculateProductAmount(BigDecimal bdPPU, BigDecimal bdProductAmount, BigDecimal bdDivisionValue) {
        BigDecimal x = bdProductAmount.divide(bdDivisionValue, 4, RoundingMode.HALF_EVEN);
        return bdPPU.multiply(x);
    }

}
