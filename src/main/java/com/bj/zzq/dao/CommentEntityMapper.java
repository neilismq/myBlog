package com.bj.zzq.dao;

import com.bj.zzq.model.CommentEntity;
import com.bj.zzq.model.CommentEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentEntityMapper {
    int countByExample(CommentEntityExample example);

    int deleteByExample(CommentEntityExample example);

    int deleteByPrimaryKey(String id);

    int insert(CommentEntity record);

    int insertSelective(CommentEntity record);

    List<CommentEntity> selectByExample(CommentEntityExample example);

    CommentEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CommentEntity record, @Param("example") CommentEntityExample example);

    int updateByExample(@Param("record") CommentEntity record, @Param("example") CommentEntityExample example);

    int updateByPrimaryKeySelective(CommentEntity record);

    int updateByPrimaryKey(CommentEntity record);
}