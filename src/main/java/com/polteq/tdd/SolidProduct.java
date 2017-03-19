package com.polteq.tdd;

/**
 * Created by Eelco de Jong on 19-3-2017.
 */
public class SolidProduct extends Product {

    private final ProductType type;
    private String calculationFactor = "100";

    SolidProduct(String name, String ppu, int amount) {
        super(name, ppu, amount);
        type = ProductType.SOLID;
    }

    String getProductType() {
        return type.toString();
    }

    String getCalculationFactor() {
        return calculationFactor;
    }

}
