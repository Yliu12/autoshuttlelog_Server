package edu.bsu.shuttlelog.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
		Query<Log> theQuery = currentSession.createQuery("from Log order by id desc", Log.class);

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
	public List<BigInteger> save(List<Log> logs) {
		Session session = sessionFactory.getCurrentSession();
		List<BigInteger> IDList = new ArrayList<BigInteger>();
		int i = 0;

		for (Log log : logs) {

			if (!searchOne(log).isEmpty()) {
				continue;
			}
			IDList.add(save(log));
			i++;
			if (i % 20 == 0) { // 20, same as the JDBC batch size
				// flush a batch of inserts and release memory:
				System.out.println("first 20 finnished");
				session.flush();
				session.clear();
			}
		}
		return IDList;
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

	// TODO Error handling, When Username exist
	@Override
	@Transactional
	public BigInteger save(Log log) {
		sessionFactory.getCurrentSession().save(log);
		return log.getId();

	}

	private List searchOne(Log log) {
		Session currentSession = sessionFactory.getCurrentSession();

		Calendar cal = Calendar.getInstance();
		cal.setTime(log.getTime());
		if (cal.get(Calendar.MILLISECOND) >= 500) {
			System.out.println("Round off milliseconds to seconds");
			cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + 1);
		}
		cal.set(Calendar.MILLISECOND, 0);
		
		Timestamp ts = new Timestamp(cal.getTimeInMillis());

		Query query = currentSession.createQuery("from Log l where l.time = :time and l.busId = :busId");
		// Timestamp ts = new Timestamp(1000 * (log.getTime().getTime()) / 1000);
		query.setParameter("time", ts);
		System.out.println(ts);
		query.setParameter("busId", log.getBusId());
		List l  = query.getResultList();
		return l;
	}

}
