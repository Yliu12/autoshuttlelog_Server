package edu.bsu.shuttlelog.service;

import java.util.List;

import edu.bsu.shuttlelog.entity.Log;

public interface LogService {
	List<Log> list();

	List<Long> save(List<Log> log);

	Log getByID(long id);

	void update(long id, Log log);
}
