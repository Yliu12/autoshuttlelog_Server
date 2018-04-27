package edu.bsu.shuttlelog.service.loop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.bsu.shuttlelog.dao.LoopDAO;
import edu.bsu.shuttlelog.entity.Loop;
import edu.bsu.shuttlelog.resppojo.RespException;

@Service
@Transactional
public class LoopServiceImpl implements LoopService {

	@Autowired
	private LoopDAO loopDAO;

	@Override
	public LoopResp list() throws Exception {
		LoopResp resp = new LoopResp();
		try {
			resp.setRespBody(loopDAO.list());
		} catch (Exception e) {
			resp.setError(new RespException("601", "Error Fetching Loop List", e));
			e.printStackTrace();
			return resp;
		}

		return resp;
	}

	@Override
	public LoopResp getByName(String loopName) throws Exception {
		LoopResp resp = new LoopResp();

		resp.setRespBody(loopDAO.getByName(loopName));

		return resp;
	}

	@Transactional
	@Override
	public LoopResp save(Loop loop) throws Exception {

		LoopResp resp = new LoopResp();

		resp.setRespBody(loopDAO.save(loop));

		return resp;

	}

	@Transactional
	@Override
	public LoopResp update(String loopName, Loop loop) throws Exception {
		LoopResp resp = new LoopResp();

		resp.setRespBody(loopDAO.update(loopName, loop));

		return resp;
	}

}
