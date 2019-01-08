package com.bj.zzq.mapper;

import com.bj.zzq.model.AdminEntity;
import com.bj.zzq.model.AdminEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminEntityMapper {
    int countByExample(AdminEntityExample example);

    int deleteByExample(AdminEntityExample example);

    int deleteByPrimaryKey(String id);

    int insert(AdminEntity record);

    int insertSelective(AdminEntity record);

    List<AdminEntity> selectByExample(AdminEntityExample example);

    AdminEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AdminEntity record, @Param("example") AdminEntityExample example);

    int updateByExample(@Param("record") AdminEntity record, @Param("example") AdminEntityExample example);

    int updateByPrimaryKeySelective(AdminEntity record);

    int updateByPrimaryKey(AdminEntity record);
}