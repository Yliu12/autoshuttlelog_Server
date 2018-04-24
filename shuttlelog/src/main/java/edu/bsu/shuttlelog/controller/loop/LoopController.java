package edu.bsu.shuttlelog.controller.loop;

import java.util.NoSuchElementException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import edu.bsu.shuttlelog.entity.Loop;
import edu.bsu.shuttlelog.resp.MyResp;
import edu.bsu.shuttlelog.resp.RespException;
import edu.bsu.shuttlelog.service.loop.LoopService;
import edu.bsu.shuttlelog.token.AuthUtil;

@Controller
public class LoopController {
	@Autowired
	private LoopService loopService;

	@GetMapping("/loops")
	public ResponseEntity<MyResp> list() {

		MyResp myresp = null;
		try {
			myresp = loopService.list();
		} catch (Exception e) {
			myresp.setError(new RespException("601", "Error Fetching Loop List", e));
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(myresp);
	}

	@GetMapping("/loop/{loopName}")
	public ResponseEntity<MyResp> getByName(@PathVariable("loopName") String loopName) {
		MyResp myresp = new MyResp();
		try {
			myresp = loopService.getByName(loopName);

		} catch (NoSuchElementException e) {
			myresp.setError(new RespException("602", "Error Fetching Loop " + loopName + "Loop Not Exist", e));
			e.printStackTrace();

		} catch (Exception e) {
			myresp.setError(new RespException("602", "Error Fetching Loop " + loopName, e));
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(myresp);
	}

	@PostMapping("/loop")
	public ResponseEntity<MyResp> save(@RequestBody Loop loop, @RequestHeader(value = "token") String token) {
		MyResp myresp = new MyResp();
		try {

			// User Authentication
			AuthUtil.userAuth(token, AuthUtil.MANAGER_CODE);

			myresp = loopService.save(loop);

		} catch (RespException e) {

			// Add this Catch if Authentication Needed
			myresp.setError(e);
			if (e.getRetCd().equals("403")) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(myresp);
			}
		} catch (ConstraintViolationException e) {
			myresp.setError(new RespException("6031", "Error Saving Loop, Loop Name Already Exist", e));
			e.printStackTrace();
		}

		catch (Exception e) {
			myresp.setError(new RespException("603", "Error Saving Loop", e));
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(myresp);
	}

	@PutMapping("/loop/{loopName}")
	public ResponseEntity<MyResp> update(@RequestBody Loop loop, @PathVariable("loopName") String loopName,
			@RequestHeader(value = "token") String token) {
		MyResp myresp = new MyResp();
		try {
			// User Authentication
			AuthUtil.userAuth(token, AuthUtil.MANAGER_CODE);
			myresp = loopService.update(loopName, loop);
		} catch (RespException e) {

			// User Auth Catch
			myresp.setError(e);
			if (e.getRetCd().equals("403")) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(myresp);
			}
		} catch (NoSuchElementException e) {
			myresp.setError(new RespException("6041", "Error Fetching Loop " + loopName + "Loop Not Exist", e));
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			myresp.setError(new RespException("6042", "Error Saving Loop, New Loop Name Already Exist", e));
			e.printStackTrace();
		} catch (Exception e) {
			myresp.setError(new RespException("604", "Error Saving Loop", e));
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(myresp);
	}

}
