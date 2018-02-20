package edu.bsu.shuttlelog.service;

import java.util.List;

import edu.bsu.shuttlelog.entity.Log;

public interface LogService {
	List<Log> list();

	long save(Log log);

	Log getByID(long id);

	void update(long id, Log log);
}
