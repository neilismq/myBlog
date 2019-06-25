package com.bj.zzq.dao;

import com.bj.zzq.mapper.SettingEntityMapper;
import com.bj.zzq.model.SettingEntity;
import com.bj.zzq.model.SettingEntityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SettingDao {
    @Autowired
    private SettingEntityMapper settingEntityMapper;

    public void update(SettingEntity entity, SettingEntityExample example){
        settingEntityMapper.updateByExampleSelective(entity,example);
    }

    public List<SettingEntity> selectByExample(SettingEntityExample example) {
        return  settingEntityMapper.selectByExample(example);
    }
}
