package com.bj.zzq.dao;

import com.bj.zzq.model.ArticleTagEntity;
import com.bj.zzq.model.ArticleTagEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleTagEntityMapper {
    int countByExample(ArticleTagEntityExample example);

    int deleteByExample(ArticleTagEntityExample example);

    int deleteByPrimaryKey(String id);

    int insert(ArticleTagEntity record);

    int insertSelective(ArticleTagEntity record);

    List<ArticleTagEntity> selectByExample(ArticleTagEntityExample example);

    ArticleTagEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ArticleTagEntity record, @Param("example") ArticleTagEntityExample example);

    int updateByExample(@Param("record") ArticleTagEntity record, @Param("example") ArticleTagEntityExample example);

    int updateByPrimaryKeySelective(ArticleTagEntity record);

    int updateByPrimaryKey(ArticleTagEntity record);
}