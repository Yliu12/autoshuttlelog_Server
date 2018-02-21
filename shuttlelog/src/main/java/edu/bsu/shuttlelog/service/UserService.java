package edu.bsu.shuttlelog.service;

import java.util.List;

import edu.bsu.shuttlelog.entity.User;

public interface UserService {
	List<User> list();

	List<Long> save(List<User> user);
	
	Long save(User user);
	
	//User getByUserName(long id);
	
	boolean longin(String userName, String Password);
	

	User update(long id, User user);
}
