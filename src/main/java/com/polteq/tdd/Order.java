package com.polteq.tdd;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private BigDecimal bdLiquidBase = new BigDecimal("0.75");
    private BigDecimal bdSolidBase = new BigDecimal("100");

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

    public BigDecimal calculateOrderAmountForClientName(String name) {
        BigDecimal orderAmount = new BigDecimal("0");
        for (ArrayList<String> list : orderList) {
            BigDecimal bdPPU = new BigDecimal(list.get(1));
            BigDecimal bdProductAmount = new BigDecimal(list.get(3));
            BigDecimal bdDivisionValue;
            if (list.get(2) == ProductType.LIQUID.toString()) {
                bdDivisionValue = new BigDecimal("0.75");
            } else {
                bdDivisionValue = new BigDecimal("100");
            }
            BigDecimal productCalculatedAmount = calculateProductAmount(bdPPU, bdProductAmount, bdDivisionValue);
            orderAmount = orderAmount.add(productCalculatedAmount);
            orderAmount = orderAmount.setScale(2, RoundingMode.CEILING);
        }
        return orderAmount;

    }

    private BigDecimal calculateProductAmount(BigDecimal bdPPU, BigDecimal bdProductAmount, BigDecimal bdDivisionValue) {
        BigDecimal x = bdProductAmount.divide(bdDivisionValue, 4, RoundingMode.HALF_EVEN);
        BigDecimal bdProductPrice = bdPPU.multiply(x);
        return bdProductPrice;
    }
}
