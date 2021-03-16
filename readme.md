# Java函数式编程

## 1.引言
   Java 8的最大变化是引入了Lambda表达式，——一种紧凑的,传递行为的方式。它使开发者在写回调函数和事件处理程序时，不必纠缠于匿名内部类的冗繁和可读性，函数式编程让事件处理系统变得更加简。    
   Lambda表达式是一个匿名方法，将行为像数据一样传递。  
   Lambda表达式需要"函数式接口"的支持，  
   函数式接口：接口中只有一个抽象方法的接口称为函数式接口，可以使用注解@FunctionalInterace修饰。
## 2.Lambda表达式
### 2.1 Lambda表达式基本形式
```
// 1.不包含参数
//使用空括号()表示没有参数，返回类型为void
Runnable thread = () -> System.out.println("hello");

// 2.包含一个参数
// 如下表达式包含且只包含一个参数，可以省略参数的括号
ActionListener one = event -> System.out.println("hello");

// 3.表达式的主体是一段代码块
Runnable thread () -> {
  System.out.println("hello");
  System.out.println("hello1");
}

// 4.表达式包含多个参数
BinaryOperator<long> add = (x,y) -> x+y;

// 5.显示声明参数类型
BinaryOperator<long> add = (long x,long y) -> x+y;

```

### 2.2 java 8 内置四大函数式核心接口
Consumer<T> : 消费型接口  
```
void accept(T t);
```
Supplier<T> : 供给型接口
```
T get();
```
Function<T, R> : 函数型接口
```
R apply(T t);
```
Predicate<T> : 断言型接口
```
boolean test(T t);
```
### 2.3 举个例子
- 1.消费型接口
```
// 消费型接口
    @Test
    public void test1(){
        goHappy(1000.2,(m) -> System.out.println(m));
    }
    public void goHappy(Double money , Consumer<Double> consumer){
        consumer.accept(money);
    }
```
- 2.供给型接口
```
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
```
- 3.函数型接口
```
// 函数型接口
    @Test
    public void test3(){
        String handler = Handler("\t\t 可口可乐 \t\t", str -> str.trim());
        System.out.println(handler);
    }
    public String Handler(String str , Function<String , String> function){
        return function.apply(str);
    }
```
- 4.断言型接口
```
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
```
## 3.Stream
Stream是用函数式编程方式在集合类上进行复杂操作的工具。  

Java 开发人员在处理集合时，一个通用的做法是在集合上进迭代，然后处理返回的每一个元素。  
如，从ArrayList集合中统计字符"a"的出现次数，传统做法：
```
   Iterator<String> iterator = list.iterator();
        int count = 0;
        while (iterator.hasNext()){
            if(iterator.next().equals("a")){
                count++;
            }
        }
```
使用Stream:
```
long count = list.stream().filter(item -> item.equals("a")).count();
```
是不是很简洁！
 
### 3.1 Stream的创建
Stream共有四种创建方式：
```
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
```
### 3.2 Stream的筛选与切片操作
|  方法   | 描述  |
|  ----  | ----  |
| filter(Predicate p)  | 接收lambda,从中间排除某些元素 |
| distinct()  | 筛选，通过流所生成的元素的hashCode()和equles()去除重复元素 |
| limit(long maxSize) | 截断流，使其元素不超过给定数量 |
| skip(long n) | 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空值。与limit(n)互补 |
- 1.filter
```
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
```
- 2.limit
```
@Test
    public void test2(){
        employees.stream()
                .limit(2)
                .forEach(System.out::println);
    }
```
- 3.skip
```
@Test
    public void test3(){
        employees.stream()
                .skip(2)
                .forEach(System.out::println);
    }
```

### 3.3 Stream的映射操作
| 方法 | 描述 |
| ---- | ---- |
| map(Function f) | 接收一个函数作为参数，该函数回被应用到每个元素上，并将其映射成一个新的元素 |
| mapToDouble(ToDoubleFunction mapper) | 接收一个函数作为参数，该函数回被应用到每个元素上，产生一个新的DoubleStream |
| mapToInt(ToIntFunction mapper) | 接收一个函数作为参数，该函数回被应用到每个元素上，产生一个新的IntStream |
| mapToLong(ToLongFunction mapper) | 接收一个函数作为参数，该函数回被应用到每个元素上，产生一个新的LongStream |
| flatMap(Function f) | 接收一个函数作为参数，将流中的每一个值都转换成另一个流，然后把所有流都连接成一个流 |

- 1.map
```
@Test
    public void test5(){
         List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
         list.stream()
                 .map(str -> str.toUpperCase())
                 .forEach(System.out::println);
    }
```

### 3.4 Stream的排序操作
| 方法 | 描述 |
| ---- | ---- |
| sorted() | 产生一个新的流，其中按自然顺序排序 |
| sorted(Comparator comp) | 产生一个新的流，其中按比较器定义的规则排序 |
- 1.sorted
```
@Test
    public void test6(){
        List<String> list = Arrays.asList("eee","aaa", "bbb", "ccc", "ddd");
        list.stream()
                .sorted()
                .forEach(System.out::println);
    }
```
- 2.sorted(Compare comp)
```
 @Test
    public void test7(){
        employees.stream()
                .sorted((e1,e2) -> {
                    return e1.getName().compareTo(e2.getName());
                })
                .forEach(System.out::println);
    }
```

### 3.5 Stream的查找与匹配操作
| 方法 | 描述 |
| ---- | ---- |
| allMatch(Predicate p) | 检查是否匹配所有元素 |
| anyMatch(Predicate p) | 检查是否至少匹配其中一个元素 |
| noneMatch(Predicate p) | 检查是否没有匹配所有元素 |
| findFirst() | 返回第一个元素 |
| findAny() | 返回当前流中的任意元素 |

- 1.allMatch
```
@Test
    public void test8(){
         boolean b = employees.stream()
                .allMatch(e -> e.getName().equals("lisi"));
        System.out.println(b);
    }
```
- 2.anyMatch
```
@Test
    public void test9(){
        boolean b = employees.stream()
                .anyMatch(e -> e.getName().equals("lisi"));
        System.out.println(b);
    }
```
- 3.noneMatch
```
@Test
    public void test10(){
        boolean b = employees.stream()
                .noneMatch(e -> e.getName().equals("lisi"));
        System.out.println(b);
    }
```
- 4.findFirst
```
@Test
    public void test11(){
         Employee employee = employees.stream()
                .findFirst()
                .get();
        System.out.println(employee.toString());
    }
```

## 3.6 Stream的规约与收集操作
-  T reduce(T identity, BinaryOperator<T> accumulator);  
将流中的元素反复结合起来，得到一个值。
```
 @Test
    public void test12(){
         List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
         Integer num = integers.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(num);
    }
```
- collect  
将流转换为其他式，接收一个Collector接口的实现。
```
@Test
    public void test13(){
         List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
         list.forEach(System.out::println);
    }
```