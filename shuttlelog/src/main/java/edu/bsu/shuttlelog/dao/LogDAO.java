package edu.bsu.shuttlelog.dao;

import java.util.List;

import edu.bsu.shuttlelog.entity.Log;

public interface LogDAO {

	public List<Log> list();


	public Log getByID(long id);


	public long save(Log log);


	public void update(long id, Log log);

}