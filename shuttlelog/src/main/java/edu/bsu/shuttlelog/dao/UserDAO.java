package edu.bsu.shuttlelog.dao;

import java.util.List;

import edu.bsu.shuttlelog.entity.User;

public interface UserDAO {

	public List<User> list();

	// public Log getUser(long userName);

	public List<Long> save(List<User> user);
	
	public Long save(User user);


	User getLogin(String userName, String Password) throws Exception;

	User update(long id, User user);

	boolean delete(long id);

}
