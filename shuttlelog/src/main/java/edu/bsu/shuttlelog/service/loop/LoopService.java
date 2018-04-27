package edu.bsu.shuttlelog.service.loop;

import edu.bsu.shuttlelog.entity.Loop;
import edu.bsu.shuttlelog.resppojo.MyResp;

public interface LoopService {
	MyResp list() throws Exception;

	MyResp getByName(String loopName) throws Exception;

	MyResp update(String loopName, Loop loop) throws Exception;

	MyResp save(Loop loop) throws Exception;
}
