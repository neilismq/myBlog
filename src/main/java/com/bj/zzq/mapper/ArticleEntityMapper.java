package com.bj.zzq.mapper;

import com.bj.zzq.model.ArticleEntity;
import com.bj.zzq.model.ArticleEntityExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleEntityMapper {
    int countByExample(ArticleEntityExample example);

    int deleteByExample(ArticleEntityExample example);

    int deleteByPrimaryKey(String id);

    int insert(ArticleEntity record);

    int insertSelective(ArticleEntity record);

    List<ArticleEntity> selectByExampleWithBLOBs(ArticleEntityExample example);

    List<ArticleEntity> selectByExample(ArticleEntityExample example);

    ArticleEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ArticleEntity record, @Param("example") ArticleEntityExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticleEntity record, @Param("example") ArticleEntityExample example);

    int updateByExample(@Param("record") ArticleEntity record, @Param("example") ArticleEntityExample example);

    int updateByPrimaryKeySelective(ArticleEntity record);

    int updateByPrimaryKeyWithBLOBs(ArticleEntity record);

    int updateByPrimaryKey(ArticleEntity record);


    List<ArticleEntity> selectArticlesByTagId(HashMap<Object, Object> map);

    List<ArticleEntity> selectArticleWithCommentCount(HashMap map);

    List<ArticleEntity> selectAllClipboardArticles();
}