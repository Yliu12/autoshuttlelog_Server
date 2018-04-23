package edu.bsu.shuttlelog.service;

import java.util.List;

public interface BaseService {

	List<Object[]> queryBySql(String sql);

	int excuteBySql(String sql);

}
