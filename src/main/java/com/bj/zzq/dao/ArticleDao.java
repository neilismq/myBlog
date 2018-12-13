package com.bj.zzq.dao;

import com.bj.zzq.model.ArticleEntity;
import com.bj.zzq.model.ArticleEntityExample;
import com.bj.zzq.model.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ArticleDao {
    @Autowired
    private ArticleEntityMapper articleEntityMapper;
    @Autowired
    private TagEntityMapper tagEntityMapper;
    public List<ArticleEntity> selectByExample(ArticleEntityExample example) {
        return articleEntityMapper.selectByExample(example);
    }

    public TagEntity selecTagByArticleId(String id) {
        HashMap<String, String> map = new HashMap();
        map.put("id",id);
        return tagEntityMapper.selecTagByArticleId(map);
    }
}
