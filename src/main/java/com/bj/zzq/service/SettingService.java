package com.bj.zzq.service;

import com.bj.zzq.dao.SettingDao;
import com.bj.zzq.model.SettingEntity;
import com.bj.zzq.model.SettingEntityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {
    @Autowired
    private SettingDao settingDao;

    public void updateSiteCount(int count){
        SettingEntity entity = new SettingEntity();
        entity.setAppValue(String.valueOf(count));
        SettingEntityExample settingEntityExample = new SettingEntityExample();
        settingEntityExample.createCriteria().andIdEqualTo("1");
        settingDao.update(entity,settingEntityExample);
    }

    public List<SettingEntity> selectAppInfo() {
        SettingEntityExample example = new SettingEntityExample();
        return settingDao.selectByExample(example);
    }
}
