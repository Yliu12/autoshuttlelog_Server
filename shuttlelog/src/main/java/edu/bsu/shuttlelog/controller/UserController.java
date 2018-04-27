package edu.bsu.shuttlelog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.bsu.shuttlelog.entity.Log;
import edu.bsu.shuttlelog.entity.User;
import edu.bsu.shuttlelog.resppojo.MyResp;
import edu.bsu.shuttlelog.resppojo.RespException;
import edu.bsu.shuttlelog.service.UserService;
import edu.bsu.shuttlelog.service.log.LogReq;
import edu.bsu.shuttlelog.service.log.LogService;
import edu.bsu.shuttlelog.token.AuthUtil;

/**
 * Created by yliu12 on 2018/2/19.
 */
@Controller

public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public ResponseEntity<MyResp> list(@RequestHeader(value = "token") String token) {
		MyResp myresp = new MyResp();
		try {
			AuthUtil.userAuth(token, AuthUtil.MANAGER_CODE);
			List<User> users = userService.list();
			myresp.setRespBody(users);
		} catch (RespException e) {
			myresp.setError(e);
			if (e.getRetCd().equals("403")) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(myresp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			myresp.setError(new RespException("10", "Error Listing User", e));
			return ResponseEntity.badRequest().body(myresp);
		}
		return ResponseEntity.ok().body(myresp);
	}

	// /*---Get a user by id---*/
	// @GetMapping("/user/{id}")
	// public ResponseEntity<Log> get(@PathVariable("id") long id) {
	// Log log = userService
	// return ResponseEntity.ok().body(log);
	// }
	//

	@PostMapping("/user")
	public ResponseEntity<?> save(@RequestBody User users, @RequestHeader(value = "token") String token) {
		MyResp myresp = new MyResp();
		Long id;
		try {
			AuthUtil.userAuth(token, AuthUtil.MANAGER_CODE);

			id = userService.save(users);
			myresp.setRespBody(id);

		} catch (RespException e) {
			myresp.setError(e);
			if (e.getRetCd().equals("403")) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(myresp);
			}
		} catch (Exception e) {
			myresp.setError(new RespException("11", "username is taken",e));
		}

		return ResponseEntity.ok().body(myresp);
	}

	@PostMapping("/users")
	public ResponseEntity<?> save(@RequestBody List<User> users, @RequestHeader(value = "token") String token) {

		MyResp myresp = new MyResp();

		try {
			AuthUtil.userAuth(token, AuthUtil.MANAGER_CODE);

			List<Long> ids = userService.save(users);

			myresp.setRespBody(ids);
		} catch (RespException e) {
			myresp.setError(e);
			if (e.getRetCd().equals("403")) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(myresp);
			}
		} catch (Exception e) {
			myresp.setError(new RespException("11", "username is taken",e));
		}
		return ResponseEntity.ok().body("New users have been saved with ID:" + myresp);

	}

	/*---Update a user by id---*/
	@PutMapping("/user/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody User user,
			@RequestHeader(value = "token") String token) {

		MyResp myresp = new MyResp();
		try {
			AuthUtil.userAuth(token, AuthUtil.MANAGER_CODE);
			myresp.setRespBody(userService.update(id, user));
		} catch (RespException e) {
			myresp.setError(e);
			if (e.getRetCd().equals("403")) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(myresp);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new RespException("12", "Error Edding User", e));
		}
		return ResponseEntity.ok().body(myresp);

	}

}
