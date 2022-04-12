package com.semanticsquare.set_demo;

import java.util.Comparator;

public class PubDateAscComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {

        int result = 0;

        if(((Book)o1).getYear() > ((Book)o2).getYear()) {
            result = 1;
        } else if (((Book)o1).getYear() < ((Book)o2).getYear()) {
            result = -1;
        }
        return result;
    }
}
