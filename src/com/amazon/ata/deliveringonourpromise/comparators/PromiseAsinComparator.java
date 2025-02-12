package com.amazon.ata.deliveringonourpromise.comparators;

import com.amazon.ata.deliveringonourpromise.types.Promise;

import java.util.Comparator;

public class PromiseAsinComparator implements Comparator<Promise> {
    @Override
    public int compare(Promise p1, Promise p2) {
        if (p1.equals(p2)) {
            return 0;
        } else {
            return (p1.getAsin().compareTo(p2.getAsin()));
        }
    }
}
