package com.bj.zzq.dao;

import com.bj.zzq.mapper.FileEntityMapper;
import com.bj.zzq.model.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/6/12
 * @Description:
 */
@Repository
public class FileDao {
    @Autowired
    private FileEntityMapper fileEntityMapper;

    public void insert(FileEntity fileEntity) {
        fileEntityMapper.insert(fileEntity);
    }
}
