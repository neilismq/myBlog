package com.bj.zzq.dao;

import com.bj.zzq.model.TagEntity;
import com.bj.zzq.model.TagEntityExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TagEntityMapper {
    int countByExample(TagEntityExample example);

    int deleteByExample(TagEntityExample example);

    int insert(TagEntity record);

    int insertSelective(TagEntity record);

    List<TagEntity> selectByExample(TagEntityExample example);

    int updateByExampleSelective(@Param("record") TagEntity record, @Param("example") TagEntityExample example);

    int updateByExample(@Param("record") TagEntity record, @Param("example") TagEntityExample example);

    TagEntity selecTagByArticleId(Map articleIdMap);

    List<TagEntity> selectAllTagWithArticleCount();
}