package com.bj.zzq.service;

import com.bj.zzq.dao.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private Test test;
    public List<Integer> getData(){
        return test.testA();
    }
}
