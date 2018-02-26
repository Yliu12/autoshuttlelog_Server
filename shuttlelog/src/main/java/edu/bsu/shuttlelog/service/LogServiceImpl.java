package edu.bsu.shuttlelog.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.bsu.shuttlelog.dao.LogDAO;
import edu.bsu.shuttlelog.entity.Log;

@Service
@Transactional(readOnly = true)
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDAO logDAO;

	
	@Override
	public List<Log> list() {

		return logDAO.list();
	}

	
	@Override
	public Log getByID(long id) {
		// TODO Auto-generated method stub
		return logDAO.getByID(id);
	}

	@Transactional
	@Override
	public List<BigInteger> save(List<Log> log) {
		// TODO Auto-generated method stub
		return logDAO.save(log);
	}

	@Transactional
	@Override
	public void update(long id, Log log) {
		 logDAO.update(id, log);
	}

}
