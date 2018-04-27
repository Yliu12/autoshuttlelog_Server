package edu.bsu.shuttlelog.dao;

import java.sql.Date;
import java.util.List;

import edu.bsu.shuttlelog.entity.DailyReportByStop;
import edu.bsu.shuttlelog.reqpojo.DataSummaryReq;
import edu.bsu.shuttlelog.resppojo.DataSummaryPojo;

public interface ReportByStopDao {
	public List<DailyReportByStop> getByDate(Date date) throws Exception;

	public List<DataSummaryPojo> getBySummary(DataSummaryReq req) throws Exception;
}
