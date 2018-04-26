package edu.bsu.shuttlelog.service.log;

import java.math.BigInteger;
import java.util.List;

import edu.bsu.shuttlelog.entity.Log;

public interface LogService {
	List<Log> list();

	List<Long> save(LogReq logReq) throws Exception;

	Log getByID(long id);

	void update(long id, Log log);
}
