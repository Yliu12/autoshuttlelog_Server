package edu.bsu.shuttlelog.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import edu.bsu.shuttlelog.entity.DailyReportByHour;
import edu.bsu.shuttlelog.reqpojo.DataSummaryReq;
import edu.bsu.shuttlelog.resppojo.DataSummaryByHour;

public interface ReportByHourService {
	public List<DailyReportByHour> getByDate(Date date) throws Exception;
	public List<DataSummaryByHour> getBySummary(DataSummaryReq req) throws Exception;

}
