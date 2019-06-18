package com.bj.zzq.dao;

import com.bj.zzq.mapper.CommentEntityMapper;
import com.bj.zzq.mapper.FileEntityMapper;
import com.bj.zzq.model.CommentEntity;
import com.bj.zzq.model.CommentEntityExample;
import com.bj.zzq.model.FileEntity;
import com.bj.zzq.model.dto.CommentUserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/6/12
 * @Description:
 */
@Repository
public class CommentDao {
    @Autowired
    private CommentEntityMapper commentEntityMapper;

    public void insert(CommentEntity commentEntity) {
        commentEntityMapper.insert(commentEntity);
    }

    public List<CommentEntity> selectByExample(CommentEntityExample example){
        return commentEntityMapper.selectByExample(example);
    }
    public List<CommentUserResp> selectAllCommentResp(Map map){
        return commentEntityMapper.selectAllCommentResp(map);
    }

    public void deleteByExample(CommentEntityExample commentEntityExample) {
        commentEntityMapper.deleteByExample(commentEntityExample);
    }
}
