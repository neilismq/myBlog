package com.bj.zzq.utils;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class FunctionTest {
    public static void main(String[] args) {
//        Stream<String> wordList = Stream.of("888", "433", "4343", "666", "777");
//        OptionalInt reduce = wordList.filter(str -> !str.contains("66")).mapToInt(str -> Integer.valueOf(str)).distinct().sorted().reduce((a, b) -> a + b);
//        System.out.println(reduce.getAsInt());
//        //System.out.println(Arrays.toString(ints));
        Stream<List<Integer>> stream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Object[] objects = stream.flatMap(map -> {
            return map.stream();
        }).toArray();
        System.out.println(Arrays.toString(objects));
    }

    public void add(FunctionInterface functionInterface,String... aaa) {
        String aa = functionInterface.test(12, 13);
        System.out.println(aa);
    }
}
