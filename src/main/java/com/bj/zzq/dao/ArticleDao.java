package com.bj.zzq.dao;

import com.bj.zzq.model.*;
import com.bj.zzq.model.dto.ArticleTagResp;
import com.bj.zzq.model.dto.CommentUserResp;
import com.bj.zzq.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    @Autowired
    private UserEntityMapper userEntityMapper;

    public List<ArticleEntity> selectByExample(ArticleEntityExample example) {
        return articleEntityMapper.selectByExample(example);
    }

    public TagEntity selecTagByArticleId(String id) {
        HashMap<String, String> map = new HashMap();
        map.put("id", id);
        return tagEntityMapper.selecTagByArticleId(map);
    }
    public List<TagEntity> selecTagByExample(TagEntityExample example) {
        return tagEntityMapper.selectByExample(example);
    }

    public List<CommentEntity> selectCommentsByExample(CommentEntityExample example) {
        return commentEntityMapper.selectByExample(example);
    }

    public List<CommentUserResp> selectCommentRespByArticleId(String id) {
        HashMap<Object, Object> map = new HashMap();
        map.put("id", id);
        return commentEntityMapper.selectCommentRespByArticleId(map);
    }

    public boolean insertComment(CommentUserResp commentUserResp) {
        UserEntityExample userEntityExample = new UserEntityExample();
        userEntityExample.createCriteria().andNameEqualTo(commentUserResp.getName()).andEmailEqualTo(commentUserResp.getEmail());
        List<UserEntity> userEntities = userEntityMapper.selectByExample(userEntityExample);
        UserEntity userEntity = null;
        if (userEntities != null && userEntities.size() > 0) {
            userEntity = userEntities.get(0);
        } else {
            userEntity = new UserEntity();
            userEntity.setId(CommonUtils.newUUID());
            userEntity.setCreateTime(new Date());
            userEntity.setEmail(commentUserResp.getEmail());
            userEntity.setPersonalSite(commentUserResp.getPersonalSite());
            userEntity.setName(commentUserResp.getName());
            userEntityMapper.insert(userEntity);
        }
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(CommonUtils.newUUID());
        commentEntity.setArticleId(commentUserResp.getArticleId());
        commentEntity.setContent(commentUserResp.getContent());
        commentEntity.setCreateTime(new Date());
        commentEntity.setUserId(userEntity.getId());
        int insertNum = commentEntityMapper.insert(commentEntity);
        if (insertNum == 1) {
            return true;
        }
        return false;
    }

    public List<ArticleTagResp> selectArticleTagRespByTagId(String tagId) {
        HashMap<Object, Object> map = new HashMap();
        map.put("id", tagId);
        return articleEntityMapper.selectArticleTagRespByTagId(map);
    }
    public List<ArticleEntity> selectArticleByExample(ArticleEntityExample example) {
        return articleEntityMapper.selectByExample(example);
    }

}
