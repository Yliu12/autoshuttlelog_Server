package edu.bsu.shuttlelog.dao;

import java.util.ArrayList;
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

import edu.bsu.shuttlelog.entity.User;

@Repository
//TODO RESTRUCTURE DAO !!
public class userDaoImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<User> list() {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<User> theQuery = currentSession.createQuery("from User", User.class);

		// execute query and get result
		List<User> users = theQuery.getResultList();
		// return result
		return users;
	}

	@Override
	@Transactional
	// TODO Error handling, When Username exist
	public List<Long> save(List<User> users) {
		Session session = sessionFactory.getCurrentSession();
		List<Long> IDList = new ArrayList<Long>();
		int i = 0;

		for (User user : users) {
			session.save(user);
			IDList.add(user.getId());
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

	// TODO Error handling, When Username exist
	@Override
	@Transactional
	public Long save(User user) {
		sessionFactory.getCurrentSession().save(user);
		return user.getId();

	}

	@Override
	@Transactional
	public User getLogin(String userName, String password) {
		Session currentSession = sessionFactory.getCurrentSession();

		// create a criteria
		// Create CriteriaBuilder
		CriteriaBuilder builder = currentSession.getCriteriaBuilder();

		// Create CriteriaQuery
		CriteriaQuery<User> query = builder.createQuery(User.class);

		Root<User> root = query.from(User.class);

		query.select(root).where(builder.equal(root.get("USERNAME"), userName));
		query.select(root).where(builder.equal(root.get("password"), password));

		// execute query and get result
		Query<User> q = currentSession.createQuery(query);

		User user = q.getSingleResult();

		return user;

	}

	@Override
	@Transactional
	public User update(long id, User user) {
		Session session = sessionFactory.getCurrentSession();
		User user2 = session.byId(User.class).load(id);
		user2.setCreateTime(user.getCreateTime());
		//user2.setCreateTime(user.getCreateTime());
		user2.setFirstName(user.getFirstName());
		user2.setLastName(user.getLastName());
		user2.setPassword(user.getPassword());
		user2.setRole(user.getRole());
		user2.setStatusCode(user.getStatusCode());
		user2.setUserName(user.getUserName());
		session.flush();
		return user2;

	}

}
