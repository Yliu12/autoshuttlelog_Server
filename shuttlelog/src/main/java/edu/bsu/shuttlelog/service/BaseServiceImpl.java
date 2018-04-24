package edu.bsu.shuttlelog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.bsu.shuttlelog.dao.BaseDao;

@Service(value = "baseService")
public class BaseServiceImpl implements BaseService {
	@Autowired
	private BaseDao baseDao;

	@Transactional
	public List queryBySql(String sql) {
		List<Object[]> list = baseDao.queryBySql(sql);
		return list;
	}

	@Transactional
	public int excuteBySql(String sql) {
		return baseDao.excuteBySql(sql);
	}
}
