package edu.bsu.shuttlelog.service;

import java.sql.Date;
import java.util.List;

import edu.bsu.shuttlelog.entity.DailyReportByHour;
import edu.bsu.shuttlelog.entity.DailyReportByStop;

public interface ReportByStopService {
	public List<DailyReportByStop> getByDate(Date date) throws Exception;
}
