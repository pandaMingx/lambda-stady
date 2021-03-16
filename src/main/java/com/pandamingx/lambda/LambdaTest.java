package com.pandamingx.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaTest {
    // 消费型接口
    @Test
    public void test1(){
        goHappy(1000.2,(m) -> System.out.println(m));
    }
    public void goHappy(Double money , Consumer<Double> consumer){
        consumer.accept(money);
    }

    //供给型接口
    @Test
    public void test2(){
        List<Integer> list = getNumList(10, () -> (int) (Math.random() * 100));
        list.stream().forEach(System.out::println);
    }

    public List<Integer> getNumList(int num , Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i< num; i++){
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }

    // 函数型接口
    @Test
    public void test3(){
        String handler = Handler("\t\t 可口可乐 \t\t", str -> str.trim());
        System.out.println(handler);
    }
    public String Handler(String str , Function<String , String> function){
        return function.apply(str);
    }

    // 断言型接口
    @Test
    public void test4(){
        List<String> list = Arrays.asList("Hadoop" , "hive" , "HBase","Zookeeper" , "Spark");
        List<String> filter = filter(list, (str) -> str.length() > 5);
        filter.forEach(System.out::println);
    }

    public List<String> filter(List<String> list , Predicate<String> predicate){
        List<String> tmpList = new ArrayList<>();
        for (String s : list) {
            if(predicate.test(s)){
                tmpList.add(s);
            }
        }
        return tmpList;
    }



}
