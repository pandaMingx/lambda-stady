package com.pandamingx.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest2 {
    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 19, 1000.0),
            new Employee("lisi", 20, 1002.0),
            new Employee("wangwu", 21, 1003.0),
            new Employee("xiaohong", 22, 1004.0),
            new Employee("xiaoming", 23, 1005.0)
    );

    @Test
    public void test1(){
        employees.stream()
                .filter(e -> e.getAge()>=21)
                .forEach(System.out::println);
    }

    @Test
    public void test2(){
        employees.stream()
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test3(){
        employees.stream()
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test5(){
         List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
         list.stream()
                 .map(str -> str.toUpperCase())
                 .forEach(System.out::println);
    }

    @Test
    public void test6(){
        List<String> list = Arrays.asList("eee","aaa", "bbb", "ccc", "ddd");
        list.stream()
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public void test7(){
        employees.stream()
                .sorted((e1,e2) -> {
                    return e1.getName().compareTo(e2.getName());
                })
                .forEach(System.out::println);
    }

    @Test
    public void test8(){
         boolean b = employees.stream()
                .allMatch(e -> e.getName().equals("lisi"));
        System.out.println(b);
    }

    @Test
    public void test9(){
        boolean b = employees.stream()
                .anyMatch(e -> e.getName().equals("lisi"));
        System.out.println(b);
    }

    @Test
    public void test10(){
        boolean b = employees.stream()
                .noneMatch(e -> e.getName().equals("lisi"));
        System.out.println(b);
    }

    @Test
    public void test11(){
         Employee employee = employees.stream()
                .findFirst()
                .get();
        System.out.println(employee.toString());
    }

    @Test
    public void test12(){
         List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
         Integer num = integers.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(num);
    }

    @Test
    public void test13(){
         List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
         list.forEach(System.out::println);
    }


}
