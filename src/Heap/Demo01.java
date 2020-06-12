package Heap;

import java.util.Comparator;
import java.util.LinkedList;

public class Demo01 implements Comparator<Demo01> {
    private String name;
    public  Demo01(String name){
        this.name = name;
    }



    @Override
    public int compare(Demo01 o1, Demo01 o2) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    public static void main(String[] args) {

    }


}
