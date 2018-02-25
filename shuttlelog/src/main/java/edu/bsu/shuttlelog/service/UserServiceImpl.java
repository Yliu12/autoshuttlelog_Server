package edu.bsu.shuttlelog.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.bsu.shuttlelog.dao.UserDAO;
import edu.bsu.shuttlelog.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<User> list() {

		return userDAO.list();
	}

	@Override
	public List<Long> save(List<User> users) {
		return userDAO.save(users);
	}

	@Override
	public Long save(User user) throws Exception{
	
		return userDAO.save(user);
	}

	@Override
	public boolean longin(String userName, String Password) throws Exception {
		return userDAO.getLogin(userName, Password) == null ? false : true;
	}

	// TODO ADD AUTHENTICATION !!!
	@Override
	public User update(long id, User userFromPost) {
		Session session = sessionFactory.getCurrentSession();

		User existingUser = (User) session.get(User.class, id);
		existingUser.setFirstName(userFromPost.getFirstName());
		existingUser.setLastName(userFromPost.getLastName());
		existingUser.setPassword(userFromPost.getPassword());
		existingUser.setRole(userFromPost.getRole());
		existingUser.setStatusCode(userFromPost.getStatusCode());
		existingUser.setUserName(userFromPost.getUserName());
		existingUser.setLastUpdateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));

		return existingUser;

	}

}
