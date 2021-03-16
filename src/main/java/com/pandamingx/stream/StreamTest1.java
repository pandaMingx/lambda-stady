package com.pandamingx.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class StreamTest1 {
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

    public void createStreamTest(){
        /**
         * 1.可以通过Collection 系列集合提供的stream()或parallelStream()
         */
         ArrayList<String> list = new ArrayList<>();
         Stream<String> stream1 = list.stream();

        /**
         * 2.通过Arrays中的静态方法stream()获取数组流
         */
         Stream<String> stream2 = Arrays.stream(new String[]{"a", "b", "c"});

        /**
         * 3.通过Stream类中的静态方法 of()
         */
         Stream<String> stream3 = Stream.of("a", "b", "c");

        /**
         * 4.创建无限流
         */
         Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
    }
}
