package com.bj.zzq.service;


import com.bj.zzq.dao.ArticleTagEntityMapper;
import com.bj.zzq.dao.TagEntityMapper;
import com.bj.zzq.model.ArticleTagEntity;
import com.bj.zzq.model.ArticleTagEntityExample;
import com.bj.zzq.model.TagEntity;
import com.bj.zzq.model.TagEntityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagEntityMapper tagEntityMapper;

    public List<TagEntity> selectByExample(TagEntityExample example) {
        return tagEntityMapper.selectByExample(example);
    }

}
