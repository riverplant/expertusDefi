package com.river.java.question04;


import java.util.ArrayList;
import java.util.List;

class Inventory<E extends Item> {

    private List<E> items = new ArrayList<>();

    public void add(E item) {
        items.add(item);
    }

    public E getFirst() {
        if (items.size() > 0) {
            return items.get(0);
        }
        return null;
    }

    public E getLast() {
        int size = items.size();
        if (size > 0) {
            return items.get(size - 1);
        }
        return null;
    }


    public boolean remove(E item) {
        return items.remove(item);
    }

    public E findFirst(String name) {
        for (E item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }
}
