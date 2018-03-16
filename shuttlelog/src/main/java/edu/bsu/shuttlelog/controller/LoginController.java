package edu.bsu.shuttlelog.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.scienjus.authorization.manager.TokenManager;

import edu.bsu.shuttlelog.entity.User;
import edu.bsu.shuttlelog.resp.MyResp;
import edu.bsu.shuttlelog.resp.RespException;
import edu.bsu.shuttlelog.service.UserService;

/**
 * Created by yliu12 on 2018/2/18.
 */
@Controller

public class LoginController {

	@Autowired
	UserService userService;
	 @Autowired
	    private TokenManager tokenManager;

	@PostMapping("/login")
	public ResponseEntity<?> update(@RequestBody User login) {
		User user = null;
		MyResp myresp = new MyResp();
		try {
			user = userService.longin(login.getUserName(), login.getPassword());
			if(user== null) {
			myresp.setError(new RespException("22", "Username password does not match.", null));
			}else {
				 String token = UUID.randomUUID().toString();
			        tokenManager.createRelationship(user.getUserName(), token);
				myresp.setRespBody(token);
			}
		} catch (javax.persistence.NoResultException e) {
			myresp.setError(new RespException("21", "Username doesn't exist", e));
		} catch (Exception e) {
			myresp.setError(new RespException("29", "Unknow Error Please See Log", e));
		}
		return ResponseEntity.ok().body(myresp);
	}
	
	

}