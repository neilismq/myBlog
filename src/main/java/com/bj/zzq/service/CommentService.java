package com.bj.zzq.service;

import com.bj.zzq.dao.CommentDao;
import com.bj.zzq.dao.FileDao;
import com.bj.zzq.model.CommentEntity;
import com.bj.zzq.model.CommentEntityExample;
import com.bj.zzq.model.FileEntity;
import com.bj.zzq.model.dto.CommentUserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/6/12
 * @Description:
 */
@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    public List<CommentEntity> selectByExample(CommentEntityExample example) {
        return commentDao.selectByExample(example);
    }

    public List<CommentUserResp> selectAllCommentResp(Map map) {
        return commentDao.selectAllCommentResp(map);
    }

    public void deleteById(String commentId) {
        CommentEntityExample commentEntityExample = new CommentEntityExample();
        commentEntityExample.createCriteria().andIdEqualTo(commentId);
        commentDao.deleteByExample(commentEntityExample);
    }
}
