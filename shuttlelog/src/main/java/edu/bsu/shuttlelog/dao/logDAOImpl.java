package edu.bsu.shuttlelog.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.bsu.shuttlelog.entity.Log;

@Repository
public class logDAOImpl implements LogDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Log> list() {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Log> theQuery = currentSession.createQuery("from Log", Log.class);

		// execute query and get result
		List<Log> logs = theQuery.getResultList();
		// return result
		return logs;
	}

	@Override
	@Transactional
	public Log getByID(long id) {
		return sessionFactory.getCurrentSession().get(Log.class, id);
	}

	@Override
	@Transactional
	public long save(Log log) {
		sessionFactory.getCurrentSession().save(log);
		return log.getId();
	}

	@Override
	@Transactional
	public void update(long id, Log log) {
		Session session = sessionFactory.getCurrentSession();
		Log log2 = session.byId(Log.class).load(id);
		log2.setBusId(log.getBusId());
		log2.setDriver(log.getDriver());
		log2.setLoopName(log.getLoopName());
		log2.setNumberBoarded(log.getNumberBoarded());
		log2.setNumberLeft(log.getNumberLeft());
		log2.setTime(log.getTime());
		session.flush();
	}

}
