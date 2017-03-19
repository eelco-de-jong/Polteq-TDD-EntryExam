package com.polteq.tdd;

/**
 * Created by Eelco de Jong on 19-3-2017.
 */
class LiquidProduct extends Product {

    private final ProductType type;
    private String defaultValue = "0.75";

    LiquidProduct(String name, String ppu, int amount) {
        super(name, ppu, amount);
        type = ProductType.LIQUID;
    }

    String getProductType() {
        return type.toString();
    }

    String getCalculationFactor() {
        return defaultValue;
    }

}
