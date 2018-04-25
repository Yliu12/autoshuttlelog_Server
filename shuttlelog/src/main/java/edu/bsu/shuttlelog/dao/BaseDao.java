package edu.bsu.shuttlelog.dao;

import java.util.List;

public interface BaseDao {

	List<Object[]> queryBySql(String sql);

	int excuteBySql(String sql);

}
