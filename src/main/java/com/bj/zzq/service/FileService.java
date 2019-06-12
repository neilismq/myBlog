package com.bj.zzq.service;

import com.bj.zzq.dao.FileDao;
import com.bj.zzq.model.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/6/12
 * @Description:
 */
@Service
public class FileService {
    @Autowired
    private FileDao fileDao;

    public void insert(FileEntity fileEntity) {
        fileDao.insert(fileEntity);
    }
}
