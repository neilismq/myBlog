package com.zzq.bj.dao;

import java.util.List;
import java.util.Map;

public interface OrmDao {
    public List<Map<String, Object>> queryForList(String sql, Object[] params);

	public Map queryForMap(String sql, Object[] params);

	public boolean save(String sql, Object[] params);

	public boolean delete(String sql, Object[] params);

	public boolean update(String sql, Object[] params);

}