package com.bj.zzq.service;


import com.bj.zzq.mapper.ArticleEntityMapper;
import com.bj.zzq.model.ArticleEntity;
import com.bj.zzq.model.ArticleEntityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleEntityMapper articleEntityMapper;

    public List<ArticleEntity> selectByExample(ArticleEntityExample example) {
        return articleEntityMapper.selectByExampleWithBLOBs(example);
    }

    public void updateByPrimaryKeySelective(ArticleEntity entity) {
        articleEntityMapper.updateByPrimaryKeySelective(entity);
    }
}
