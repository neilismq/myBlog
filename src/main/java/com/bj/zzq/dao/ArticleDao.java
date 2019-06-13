package com.bj.zzq.dao;

import com.bj.zzq.mapper.*;
import com.bj.zzq.model.*;
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
    @Autowired
    private ArticleTagEntityMapper articleTagEntityMapper;

    public List<ArticleEntity> selectByExample(ArticleEntityExample example) {
        return articleEntityMapper.selectByExample(example);
    }

    public TagEntity selectTagByArticleId(String id) {
        HashMap<String, String> map = new HashMap();
        map.put("id", id);
        return tagEntityMapper.selecTagByArticleId(map);
    }

    public List<TagEntity> selecTagByExample(TagEntityExample example) {
        return tagEntityMapper.selectByExample(example);
    }

    public int selectArticleCountByExample(ArticleEntityExample example) {
        return articleEntityMapper.countByExample(example);
    }

    public int selectCommentsCountByExample(CommentEntityExample example) {
        return commentEntityMapper.countByExample(example);
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
        commentUserResp.setCommentId(commentEntity.getId());
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

    public List<ArticleEntity> selectArticlesByTagId(String tagId) {
        HashMap<Object, Object> map = new HashMap();
        map.put("id", tagId);
        return articleEntityMapper.selectArticlesByTagId(map);
    }

    public List<ArticleEntity> selectArticleByExample(ArticleEntityExample example) {
        return articleEntityMapper.selectByExample(example);
    }
    public List<TagEntity> selectAllTagWithArticleCount(){
        return tagEntityMapper.selectAllTagWithArticleCount();
    }

    public List<ArticleEntity> selectArticleWithCommentCount(HashMap<Object, Object> param) {

        return articleEntityMapper.selectArticleWithCommentCount(param);
    }

    public List<ArticleEntity> selectAllClipboardArticles() {
        return articleEntityMapper.selectAllClipboardArticles();
    }

    public void insertArticle(ArticleEntity articleEntity){
        articleEntityMapper.insert(articleEntity);
    }
}
