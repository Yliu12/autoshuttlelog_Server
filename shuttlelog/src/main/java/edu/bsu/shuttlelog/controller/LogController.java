package edu.bsu.shuttlelog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.bsu.shuttlelog.entity.Log;
import edu.bsu.shuttlelog.service.LogService;

/**
 * Created by yliu12 on 2018/2/19.
 */
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
   public ResponseEntity<?> save(@RequestBody List<Log> logs) {
	      List<Long> idList = logService.save(logs);
	      return ResponseEntity.ok().body("New log has been saved with ID:" + idList);
	   }

	 /*---Update a log by id---*/
	   @PutMapping("/log/{id}")
	   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Log log) {
		   logService.update(id, log);
	      return ResponseEntity.ok().body("log has been updated successfully.");
	   }

}
