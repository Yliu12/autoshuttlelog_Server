package edu.bsu.shuttlelog.service;

import java.sql.Date;
import java.util.List;

import edu.bsu.shuttlelog.entity.DailyReportByHour;

public interface DailyReportHourService {
	public List<DailyReportByHour> getByDate(Date date) throws Exception;
}
