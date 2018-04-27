package edu.bsu.shuttlelog.dao;

import java.sql.Date;
import java.util.List;

import edu.bsu.shuttlelog.entity.DailyReportByHour;
import edu.bsu.shuttlelog.entity.DailyReportByStop;

public interface ReportByStopDao {
	public List<DailyReportByStop> getByDate(Date date) throws Exception;
}
