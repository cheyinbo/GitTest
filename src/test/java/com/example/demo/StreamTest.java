package com.example.demo;

import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class StreamTest {

    private static List<User> users = null;

    static {
        users = new ArrayList<>();
        User user1 = new User(1L,"zhangsan",15,"zhangsan@qq,com");
        User user2 = new User(2L,"lisi",30,"zhangsan@qq,com");
        User user3 = new User(3L,"wangwu",18,"zhangsan@qq,com");
        User user4 = new User(4L,"zhaoliu",12,"zhangsan@qq,com");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }


    /**
     * filter筛选用户年龄小于30并返回集合并输出
     */
    @Test
    public void testStream(){
        List<User> list = users.stream().filter(d -> d.getAge() < 30).collect(Collectors.toList());
        for (User user : list) {
            System.out.println(user);
        }
    }

    /**
     * 集合以流的方式遍历输出
     */
    @Test
    public void testStream2(){
        Stream<User> test = users.stream();
        test.forEach(System.out::println);
    }

    /**
     * 单线程和多线程(parallel)计算筛选集合数组以"z"开头的字母
     */
    @Test
    public void testStream3(){
        //单线程耗时
        long time = System.currentTimeMillis();
        users.stream().parallel().filter(user -> user.getName().startsWith("z"))
                .forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - time);

        time = System.currentTimeMillis();
        users.stream().parallel().filter(user -> user.getName().startsWith("z"))
                .forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - time);

    }

    /**
     * distinct 不重复筛选
     */
    @Test
    public void testStream4(){
        List<Integer> numbers = Arrays.asList(1,5,1,9,55,4,23,2,9,10);
        numbers.stream().distinct().forEach(System.out::println);   //1,5,9,55,4,23,2,10
    }

    /**
     * distinct 不重复筛选
     * limit 限定返回集合长度,不会对集合数据进行排序操作
     */
    @Test
    public void testStream5(){
        List<Integer> numbers = Arrays.asList(1,5,1,9,55,4,23,2,9,10);
        numbers.stream().filter(number -> number % 2 == 0).distinct().limit(3).forEach(System.out::println);
    }

    /**
     * distinct 不重复筛选
     * skip跳过前两个集合数据
     */
    @Test
    public void testStream6(){
        List<Integer> numbers = Arrays.asList(1,5,1,9,55,4,23,2,9,10);
        numbers.stream().distinct().skip(2).forEach(System.out::println);
    }

    /**
     * map函数接收一个函数作为参数并应用到每个元素上,将其映射成一个新的函数
     */
    @Test
    public void testStream7(){
        users.stream().filter(user -> user.getName().startsWith("z"))
                .map(user -> user.getName())
                .map(String::length)
                .forEach(System.out::println);
    }


    @Test
    public void testStream8(){
        List<String> words = Arrays.asList("Hello","world");
        words.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @Test
    public void testStream9(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
//        int sum = numbers.stream().reduce(0,(a,b) -> a + b);

//        int sum = numbers.stream().reduce(0,Integer::sum);
//        System.out.println(sum);

        //无初始值
        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
        System.out.println(sum.get());

        //获取集合最小值
//        Optional<Integer> min = numbers.stream().reduce(Integer::sum);
//        System.out.println(sum.get());
    }

}
