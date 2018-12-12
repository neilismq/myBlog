package com.bj.zzq.service;


import com.bj.zzq.dao.ArticleEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    private ArticleEntityMapper articleEntityMapper;
}
