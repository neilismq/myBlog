package com.bj.zzq.mapper;

import com.bj.zzq.model.SettingEntity;
import com.bj.zzq.model.SettingEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SettingEntityMapper {
    int countByExample(SettingEntityExample example);

    int deleteByExample(SettingEntityExample example);

    int insert(SettingEntity record);

    int insertSelective(SettingEntity record);

    List<SettingEntity> selectByExample(SettingEntityExample example);

    int updateByExampleSelective(@Param("record") SettingEntity record, @Param("example") SettingEntityExample example);

    int updateByExample(@Param("record") SettingEntity record, @Param("example") SettingEntityExample example);
}