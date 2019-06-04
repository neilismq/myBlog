package com.bj.zzq.service;

import com.bj.zzq.dao.AdminDao;
import com.bj.zzq.model.AdminEntity;
import com.bj.zzq.model.AdminEntityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/6/4
 * @Description:
 */
@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;

    public AdminEntity selectAdminByUsername(String username) {
        AdminEntityExample adminEntityExample = new AdminEntityExample();
        adminEntityExample.createCriteria().andUsernameEqualTo(username);
        List<AdminEntity> adminEntities = adminDao.selectUserEntityByExample(adminEntityExample);
        if (adminEntities != null && adminEntities.size() > 0) {
            return adminEntities.get(0);
        }
        return null;
    }
}
