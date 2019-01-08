package com.bj.zzq.dao;

import com.bj.zzq.mapper.AdminEntityMapper;
import com.bj.zzq.model.AdminEntity;
import com.bj.zzq.model.AdminEntityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private AdminEntityMapper adminEntityMapper;

    public List<AdminEntity> selectUserEntityByExample(AdminEntityExample example) {
        return adminEntityMapper.selectByExample(example);
    }
}
