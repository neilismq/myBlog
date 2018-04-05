package com.zzq.bj.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by zzq on 2018/3/12.
 */
public class OrmDaoImpl implements OrmDao {
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> queryForList(String sql, Object[] params) {
        return this.jdbcTemplate.queryForList(sql, params);

    }
    public Map queryForMap(String sql, Object[] params) {
        return jdbcTemplate.queryForMap(sql, params);
    }

    public boolean save(String sql, Object[] params) {
        return jdbcTemplate.update(sql, params) > 0 ? true : false;
    }

    public boolean delete(String sql, Object[] params) {
        return jdbcTemplate.update(sql, params) > 0 ? true : false;
    }

    public boolean update(String sql, Object[] params) {
        return jdbcTemplate.update(sql, params) > 0 ? true : false;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
