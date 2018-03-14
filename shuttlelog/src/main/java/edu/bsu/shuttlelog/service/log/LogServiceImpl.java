package edu.bsu.shuttlelog.service.log;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.bsu.shuttlelog.dao.LogDAO;
import edu.bsu.shuttlelog.entity.Log;
import edu.bsu.shuttlelog.entity.User;
import edu.bsu.shuttlelog.resp.RespException;
import edu.bsu.shuttlelog.service.UserService;

@Service
@Transactional(readOnly = true)
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDAO logDAO;

	@Autowired
	UserService userService;

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
	public List<BigInteger> save(LogReq logReq) throws Exception {
//		if (logReq.getUser() == null || logReq.getUser().getUserName() == null) {
//			throw new RespException("331", "Authentication failed, no user info", null);
//		}
		return logDAO.save(logReq.getLogList());
//		TODO authentication
//		User login = logReq.getUser();
//		User user = null;
//
//		user = userService.longin(login.getUserName(), login.getPassword());
//		if (user == null) {
//			throw new RespException("322", "Authentication failed, Please check Username & Password.", null);
//		} else {
//			
//		}
	}

	@Transactional
	@Override
	public void update(long id, Log log) {
		logDAO.update(id, log);
	}

}
