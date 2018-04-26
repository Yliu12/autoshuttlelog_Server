package edu.bsu.shuttlelog.controller.log;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import edu.bsu.shuttlelog.entity.Log;
import edu.bsu.shuttlelog.resp.MyResp;
import edu.bsu.shuttlelog.resp.RespException;
import edu.bsu.shuttlelog.service.log.LogReq;
import edu.bsu.shuttlelog.service.log.LogService;
import edu.bsu.shuttlelog.token.AuthUtil;

/**
 * Created by yliu12 on 2018/2/19.
 */

@CrossOrigin(origins = "*")
@Controller
public class LogController {

	@Autowired
	private LogService logService;

	@GetMapping("/log")
	public ResponseEntity<List<Log>> list() {
		List<Log> logs = logService.list();
		return ResponseEntity.ok().body(logs);
	}

	/*---Get a book by id---*/
	@GetMapping("/log/{id}")
	public ResponseEntity<Log> get(@PathVariable("id") long id) {
		Log log = logService.getByID(id);
		return ResponseEntity.ok().body(log);
	}

	@PostMapping("/logs")
	public ResponseEntity<?> save(@RequestBody LogReq logreq, @RequestHeader(value = "token") String token) {
		MyResp myresp = new MyResp();
		List<Long> idList = null;

		try {
			// driver authentication. Throw RespException error 403 if not authorized.
			AuthUtil.userAuth(token, AuthUtil.DRIVER_CODE);

			idList = logService.save(logreq);
			myresp.setRespBody(idList);
		} catch (javax.persistence.NoResultException e) {
			myresp.setError(new RespException("321", "Username doesn't exist", e));
		} catch (RespException e) {
			myresp.setError(e);

			if (e.getRetCd().equals("403")) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(myresp);
			}
		} catch (Exception e) {
			myresp.setError(new RespException("31", "Unknow error adding logs", e));
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(myresp);
	}

	/*---Update a log by id---*/
	@PutMapping("/log/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Log log) {
		logService.update(id, log);
		return ResponseEntity.ok().body("log has been updated successfully.");
	}

}
