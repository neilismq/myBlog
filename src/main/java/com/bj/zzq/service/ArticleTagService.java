package com.bj.zzq.service;


import com.bj.zzq.mapper.ArticleTagEntityMapper;
import com.bj.zzq.model.ArticleTagEntity;
import com.bj.zzq.model.ArticleTagEntityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleTagService {
    @Autowired
    private ArticleTagEntityMapper articleTagEntityMapper;

    public List<ArticleTagEntity> selectByExample(ArticleTagEntityExample example) {
        return articleTagEntityMapper.selectByExample(example);
    }

    public void deleteByTagId(String tagId){
        ArticleTagEntityExample articleTagEntityExample = new ArticleTagEntityExample();
        articleTagEntityExample.createCriteria().andTagIdEqualTo(tagId);
        articleTagEntityMapper.deleteByExample(articleTagEntityExample);
    }
}
