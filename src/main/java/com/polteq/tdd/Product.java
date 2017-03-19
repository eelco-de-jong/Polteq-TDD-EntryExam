package com.polteq.tdd;

import java.math.BigDecimal;

/**
 * Created by Eelco de Jong on 16-3-2017.
 */
public class Product {
    private String productName;
    private BigDecimal productPricePerUnit;
    private int productAmount;

    public Product(String name, String ppu, int amount) {
        productName = name;
        productPricePerUnit = getProductPricePerUnit(ppu);
        productAmount = amount;
    }

    private BigDecimal getProductPricePerUnit(String ppu) {
        return new BigDecimal(ppu);
    }

    String getProductName() {
        return productName;
    }

    String getProductPPU() {
        return productPricePerUnit.toString();
    }

    int getProductAmount() {
        return productAmount;
    }

}
