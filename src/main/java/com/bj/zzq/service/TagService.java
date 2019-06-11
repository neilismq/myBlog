package com.bj.zzq.service;


import com.bj.zzq.mapper.TagEntityMapper;
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

    public void insert(TagEntity tagEntity) {
        tagEntityMapper.insert(tagEntity);
    }

    public void update(TagEntity tagEntity, TagEntityExample tagEntityExample) {
        tagEntityMapper.updateByExampleSelective(tagEntity, tagEntityExample);
    }

    public void deleteById(String tagId) {
        TagEntityExample tagEntityExample = new TagEntityExample();
        tagEntityExample.createCriteria().andIdEqualTo(tagId);
        tagEntityMapper.deleteByExample(tagEntityExample);
    }
}
