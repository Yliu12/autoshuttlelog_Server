package edu.bsu.shuttlelog.dao;

import java.sql.Date;
import java.util.List;

import edu.bsu.shuttlelog.entity.DailyReportByHour;
import edu.bsu.shuttlelog.entity.DailyReportByStop;

public interface DailyReportHourDao {
	public List<DailyReportByHour> getByDate(Date date) throws Exception;
}
