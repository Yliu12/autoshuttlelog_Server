package edu.bsu.shuttlelog.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.bsu.shuttlelog.entity.User;
import edu.bsu.shuttlelog.resp.MyResp;
import edu.bsu.shuttlelog.resp.RespException;
import edu.bsu.shuttlelog.service.UserService;
import edu.bsu.shuttlelog.token.AuthUtil;
import edu.bsu.shuttlelog.token.JavaWebToken;

/**
 * Created by yliu12 on 2018/2/18.
 */
@Controller

public class LoginController {

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User login) {
		User user = null;
		MyResp myresp = new MyResp();
		try {
			user = userService.longin(login.getUserName(), login.getPassword());
			if (user == null) {
				myresp.setError(new RespException("22", "Username password does not match.", null));
			} else {
				HashMap<String, Object> respBody = new HashMap<String, Object>();

				// set User Info
				HashMap<String, Object> loginInfo = new HashMap<String, Object>();
				loginInfo.put("id", user.getId());
				loginInfo.put("role", user.getRole());
				loginInfo.put("userName", user.getUserName());
				String sessionId = JavaWebToken.createJavaWebToken(loginInfo);
				System.out.println("sessionID" + sessionId);

				respBody.put("token", sessionId);
				respBody.putAll(loginInfo);
				// respBody.put("token", token);

				myresp.setRespBody(respBody);
			}
		} catch (javax.persistence.NoResultException e) {
			myresp.setError(new RespException("21", "Username doesn't exist", e));
			e.printStackTrace();
		} catch (Exception e) {
			myresp.setError(new RespException("29", "Unknow Error Please See Log", e));
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(myresp);
	}

	@PostMapping("/driverlogin")
	public ResponseEntity<?> driverLogin(@RequestBody User login) {
		User user = null;
		MyResp myresp = new MyResp();
		try {
			user = userService.longin(login.getUserName(), login.getPassword());
			if (user == null) {
				myresp.setError(new RespException("22", "Username password does not match.", null));
			} else {
				HashMap<String, Object> respBody = new HashMap<String, Object>();

				if (user.getRole().equals(AuthUtil.DRIVER_CODE)) {
					// set User Info
					HashMap<String, Object> loginInfo = new HashMap<String, Object>();
					loginInfo.put("id", user.getId());
					loginInfo.put("role", user.getRole());
					loginInfo.put("userName", user.getUserName());
					String sessionId = JavaWebToken.createJavaWebToken(loginInfo);
					System.out.println("sessionID" + sessionId);

					respBody.put("token", sessionId);
					respBody.putAll(loginInfo);
					// respBody.put("token", token);

					myresp.setRespBody(respBody);
				} else {
					myresp.setError(new RespException("403", "Wrong Username Or Password", null));
				}
			}
		} catch (javax.persistence.NoResultException e) {
			myresp.setError(new RespException("21", "Username doesn't exist", e));
			e.printStackTrace();
		} catch (Exception e) {
			myresp.setError(new RespException("29", "Unknow Error Please See Log", e));
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(myresp);
	}

}