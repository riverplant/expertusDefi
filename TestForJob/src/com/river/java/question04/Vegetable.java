package com.river.java.question04;

class Vegetable extends Item {

    private String type;
    private String origin;

    Vegetable(String name, String type, String origin, int quantity, double price) {
        super(name, quantity, price);
        this.type = type;
        this.origin = origin;
    }

    public String getType() {
        return type;
    }

    public String getOrigin() {
        return origin;
    }
}
