package com.example.demo.ide.Domain.Editor.VO;

import java.util.ArrayList;

public class FixedList<T> {
    private ArrayList<T> list = new ArrayList<>();

    private final int maxLength;

    public FixedList(int length) {
        this.maxLength = length;
    }

    public boolean add(T item) {
        if(this.count() == this.maxLength) {
            this.list.remove(0);
        }

        if(this.contains(item)) {
            return false;
        }

        this.list.add(item);

        return true;
    }

    public boolean remove(T item) {
        if(!this.contains(item)) {
            return false;
        }

        this.list.remove(item);

        return true;
    }

    public int count() {
        return (int) this.list.stream().count();
    }

    public boolean contains(T item) {
        for (T t: this.list) {
            if (t.toString().equals(item.toString())) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<T> getItems() {
        return this.list;
    }
}
