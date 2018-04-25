package edu.bsu.shuttlelog.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository(value = "baseDao")
public class BaseDaoImpl implements BaseDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public List queryBySql(String sql) {
		List<Object[]> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	public int excuteBySql(String sql) {
		int result;
		SQLQuery query = this.getSession().createSQLQuery(sql);
		result = query.executeUpdate();
		return result;
	}

}
