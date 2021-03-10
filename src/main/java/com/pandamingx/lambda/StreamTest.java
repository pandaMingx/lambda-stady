package com.pandamingx.lambda;

import java.util.ArrayList;
import java.util.Iterator;

public class StreamTest {
    public static void main(String[] args) {

        final ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("c");
        list.add("c");

//        test1(list);
        test2(list);
    }

    private static void test1(ArrayList<String> list){

        final Iterator<String> iterator = list.iterator();
        int count = 0;
        while (iterator.hasNext()){
            if(iterator.next().equals("a")){
                count++;
            }
        }

        System.out.println(count);
    }
    
    private static void test2(final ArrayList<String> list){
        long count = list.stream().filter(item -> item.equals("a")).count();
        System.out.println(count);
    }
}
