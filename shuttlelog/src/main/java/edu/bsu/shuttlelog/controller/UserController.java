package edu.bsu.shuttlelog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.bsu.shuttlelog.entity.Log;
import edu.bsu.shuttlelog.entity.User;
import edu.bsu.shuttlelog.resp.RespException;
import edu.bsu.shuttlelog.service.UserService;
import edu.bsu.shuttlelog.service.log.LogService;

/**
 * Created by yliu12 on 2018/2/19.
 */
@Controller

public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public ResponseEntity<List<User>> list() {
		List<User> users = userService.list();
		return ResponseEntity.ok().body(users);
	}

//	 /*---Get a user by id---*/
//	 @GetMapping("/user/{id}")
//	 public ResponseEntity<Log> get(@PathVariable("id") long id) {
//	 Log log = userService
//	 return ResponseEntity.ok().body(log);
//	 }
//	

	@PostMapping("/user")
	public ResponseEntity<?> save(@RequestBody User users) {
		Long id ;
		try {
			id = userService.save(users);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new RespException("11", "username is taken",e));
		}
		return ResponseEntity.ok().body("New user has been saved with ID:" + id);
	}

	@PostMapping("/users")
	public ResponseEntity<?> save(@RequestBody List<User> users) {
		List<Long> id = userService.save(users);
		return ResponseEntity.ok().body("New users have been saved with ID:" + id);
	}

	/*---Update a user by id---*/
	@PutMapping("/user/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody User user) {
		userService.update(id, user);
		return ResponseEntity.ok().body("log has been updated successfully.");
	}

}
