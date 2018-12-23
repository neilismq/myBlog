package com.bj.zzq.dao;

import com.bj.zzq.model.ArticleEntity;
import com.bj.zzq.model.ArticleEntityExample;

import java.util.HashMap;
import java.util.List;

import com.bj.zzq.model.dto.ArticleTagResp;
import org.apache.ibatis.annotations.Param;

public interface ArticleEntityMapper {
    int countByExample(ArticleEntityExample example);

    int deleteByExample(ArticleEntityExample example);

    int insert(ArticleEntity record);

    int insertSelective(ArticleEntity record);

    List<ArticleEntity> selectByExampleWithBLOBs(ArticleEntityExample example);

    List<ArticleEntity> selectByExample(ArticleEntityExample example);

    int updateByExampleSelective(@Param("record") ArticleEntity record, @Param("example") ArticleEntityExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticleEntity record, @Param("example") ArticleEntityExample example);

    int updateByExample(@Param("record") ArticleEntity record, @Param("example") ArticleEntityExample example);

    List<ArticleEntity> selectArticlesByTagId(HashMap<Object, Object> map);

    List<ArticleEntity> selectArticleWithCommentCount(HashMap<Object, Object> param);

    List<ArticleEntity> selectAllClipboardArticles();
}