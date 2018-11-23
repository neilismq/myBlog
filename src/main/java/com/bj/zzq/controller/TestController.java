package com.bj.zzq.controller;

import com.bj.zzq.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@RestController
@RequestMapping("/q")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/a")
    public Object test() {
        List<Integer> test = testService.getData();
        return test;
    }

    public static void main(String[] args) {
        //IntStream.of(1,3,6).forEach(System.out::println);
        Stream<String> stream = Stream.of("12", "32", "333");
        stream.toArray(String[]::new);
        new Thread(() -> {
            System.out.println("123");
        });
    }
}
