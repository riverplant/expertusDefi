package com.river.java.question04;
/**
 * 
 * @author riverplant
 *
 */
class Fruit extends Item {
    private String color;

    Fruit(String name, String color, int quantity, double price) {
        super(name, quantity, price);
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
