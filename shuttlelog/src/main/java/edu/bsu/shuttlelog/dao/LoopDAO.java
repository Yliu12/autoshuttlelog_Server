package edu.bsu.shuttlelog.dao;

import java.util.List;

import edu.bsu.shuttlelog.entity.Loop;

public interface LoopDAO {

	public List<Loop> list();

	public Loop getByName(String loopName) throws Exception;

	public long save(Loop loop) throws Exception;

	public Loop update(String loopName, Loop loop) throws Exception;
}
