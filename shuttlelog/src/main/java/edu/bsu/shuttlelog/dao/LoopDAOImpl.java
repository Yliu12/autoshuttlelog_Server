package edu.bsu.shuttlelog.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.bsu.shuttlelog.entity.Loop;


@Repository
public class LoopDAOImpl implements LoopDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Loop> list(){
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Loop> theQuery = currentSession.createQuery("from Loop", Loop.class);

		// execute query and get result
		List<Loop> loops = theQuery.getResultList();
		// return result
		return loops;
	}

	@Override
	@Transactional
	public Loop getByName(String loopName) throws Exception {
		Session session = sessionFactory.getCurrentSession();

		// Create CriteriaBuilder
		CriteriaBuilder builder = session.getCriteriaBuilder();

		// Create CriteriaQuery
		CriteriaQuery<Loop> query = builder.createQuery(Loop.class);
		Root<Loop> root = query.from(Loop.class);
		query.select(root).where(builder.equal(root.get("loopName"), loopName));

		// execute query and get result
		Query<Loop> q = session.createQuery(query);
		Loop loop = q.getSingleResult();

		return loop;
	}

	@Override
	@Transactional
	public BigInteger save(Loop loop) throws Exception {
		loop.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
		loop.setLastUpdateTime(loop.getLastUpdateTime());
		sessionFactory.getCurrentSession().save(loop);
		return loop.getId();
	}

	@Override
	@Transactional
	public Loop update(String loopName, Loop loop) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Loop loop2 = getByName(loopName);
		loop2.setLoopName(loop.getLoopName());
		loop2.setStops(loop.getStops());
		loop2.setLastUpdateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
		loop2.setStatusCode(loop.getStatusCode());

		session.flush();
		return loop2;
	}

}
