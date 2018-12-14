package com.bj.zzq.dao;

import com.bj.zzq.model.*;
import com.bj.zzq.model.dto.CommentResp;
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
    @Autowired
    private CommentEntityMapper commentEntityMapper;
    public List<ArticleEntity> selectByExample(ArticleEntityExample example) {
        return articleEntityMapper.selectByExample(example);
    }

    public TagEntity selecTagByArticleId(String id) {
        HashMap<String, String> map = new HashMap();
        map.put("id",id);
        return tagEntityMapper.selecTagByArticleId(map);
    }
    public List<CommentEntity> selectCommentsByExample(CommentEntityExample example){
        return commentEntityMapper.selectByExample(example);
    }
    public List<CommentResp> selectCommentRespByArticleId(String id){
        HashMap<Object, Object> map = new HashMap();
        map.put("id",id);
        return commentEntityMapper.selectCommentRespByArticleId(map);
    }
}
